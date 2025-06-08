package com.campestre.clube.backend_application.service;

import com.campestre.clube.backend_application.entity.Place;
import com.campestre.clube.backend_application.exceptions.ConflictException;
import com.campestre.clube.backend_application.exceptions.NotFoundException;
import com.campestre.clube.backend_application.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceService {

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private AddressService addressService;

    public Place getById(Integer id) {
        existsByIdOrThrow(id);
        return placeRepository.findById(id).get();
    }

    public List<Place> getAllOrderedByRating() {
        return placeRepository.findAllByOrderByRatingDesc();
    }

    public Place save(Place place) {
        if (placeRepository.existsByName(place.getName()))
            throw new ConflictException("Já existe um local cadastrado com esse nome.");
        if (addressService.addressAlreadyExists(place.getAddress()))
            throw new ConflictException("Este endereço já está cadastrado em nosso sistema.");

        place.setAddress(addressService.saveIfNotExist(place.getAddress()));
        return placeRepository.save(place);
    }

    public Place update(Integer id, Place newPlace){
        existsByIdOrThrow(id);
        if (placeRepository.existsByNameAndIdNot(newPlace.getName(), id))
            throw new ConflictException("Já existe um local cadastrado com esse nome.");

        newPlace.setAddress(addressService.update(newPlace.getAddress().getId(), newPlace.getAddress()));
        newPlace.setId(id);
        return placeRepository.save(newPlace);
    }

    public void delete(Integer id){
        existsByIdOrThrow(id);
        placeRepository.deleteById(id);
    }



    private void existsByIdOrThrow(Integer id) {
        if (!placeRepository.existsById(id))
            throw new NotFoundException("Não encontramos o local solicitado.");
    }
}
