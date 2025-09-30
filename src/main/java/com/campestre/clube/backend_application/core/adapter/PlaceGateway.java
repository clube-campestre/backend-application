package com.campestre.clube.backend_application.core.adapter;

import com.campestre.clube.backend_application.core.domain.Place;

import java.util.List;

public interface PlaceGateway {
    boolean existsByNameIgnoreCase(String name);
    boolean existsByNameIgnoreCaseAndIdNot(String name, Integer id);
    boolean existsById(Integer id);
    Place findById(Integer id);
    List<Place> findOrderedByRatingDesc();
    Place save(Place place);
    void removeById(Integer id);
}
