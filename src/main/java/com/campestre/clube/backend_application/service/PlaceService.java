package com.campestre.clube.backend_application.service;

import com.campestre.clube.backend_application.controller.dtos.requests.SaveAddressRequestDto;
import com.campestre.clube.backend_application.controller.dtos.requests.SavePlaceRequestDto;
import com.campestre.clube.backend_application.controller.dtos.responses.SavePlaceResponseDto;
import com.campestre.clube.backend_application.controller.mapper.PlaceMapper;
import com.campestre.clube.backend_application.entity.Address;
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

    public List<Place> getAll() {
        return placeRepository.findAll();
    }

    public Place getById(Integer id) {
        Optional<Place> place = placeRepository.findById(id);
        placeNotFoundValidation(place, id);

        return place.get();
    }

    public List<Place> getAllOrderedByRating() {
        return placeRepository.findAllByOrderByRatingDesc();
    }

    public SavePlaceResponseDto save(SavePlaceRequestDto place) {
        if (placeRepository.existsBySirname(place.getSirname()))
            throw new ConflictException("Place with existing sirname");
        Address address = addressService.createAddress(place.getAddress());

        //TODO: Implementar validações na lógica de validar existencia pelo cep, já que pode ter o mesmo cep e ser de outro numero
        if(!addressService.addressAlreadyExists(address.getCep())) addressService.register(address);
        place.setFkAddress(address.getId());

        Place savePlace = placeRepository.save(PlaceMapper.toEntity(place));

        SavePlaceResponseDto response = PlaceMapper.toSaveResponse(savePlace, address);

        return response;
    }

    public SavePlaceResponseDto update(Integer id, SavePlaceRequestDto newPlace){
        Optional<Place> place = placeRepository.findById(id);

        placeNotFoundValidation(place, id);
        if (placeRepository.existsBySirname(newPlace.getSirname()))
            throw new ConflictException("Place with existing sirname");


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
