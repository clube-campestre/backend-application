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
        Optional<Place> place = placeRepository.findById(id);
        placeNotFoundValidation(place, id);

        return place.get();
    }

    public List<Place> getAllOrderedByRating() {
        return placeRepository.findAllByOrderByRatingDesc();
    }

    public Place save(Place place) {
        if (placeRepository.existsByName(place.getName()))
            throw new ConflictException("Place with existing name");
        if (addressService.addressAlreadyExists(place.getAddress().getCep(), place.getAddress().getHouseNumber()))
            throw new ConflictException("Address already found for provided CEP.");

        place.setAddress(addressService.saveIfNotExist(place.getAddress()));
        return placeRepository.save(place);
    }

    public Place update(Integer id, Place newPlace){
        Optional<Place> place = placeRepository.findById(id);

        placeNotFoundValidation(place, id);
        if (placeRepository.existsByNameAndIdNot(newPlace.getName(), id))
            throw new ConflictException("Place with existing sirname");

        newPlace.setAddress(addressService.update(newPlace.getAddress().getId(), newPlace.getAddress()));
        newPlace.setId(id);
        return placeRepository.save(newPlace);
    }

    public void delete(Integer id){
        if(!placeRepository.existsById(id))
            throw new NotFoundException("User by id [%s] not found".formatted(id));

        placeRepository.deleteById(id);
    }



    private void placeNotFoundValidation(Optional<Place> place, Integer id) {
        if (place.isEmpty())
            throw new NotFoundException("Place by id [%s] not found".formatted(id));
    }
}
