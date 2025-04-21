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

    public Place save(Place place) {
        if (placeRepository.existsBySirname(place.getSirname()))
            throw new ConflictException("Place with existing sirname");

        return placeRepository.save(place);
    }

    public Place update(Integer id, Place newPlace){
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
