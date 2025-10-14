package com.campestre.clube.backend_application.core.adapter;

import com.campestre.clube.backend_application.core.domain.Place;

import java.util.List;

public interface PlaceGateway {
    boolean existsByNameIgnoreCase(String name);
    boolean existsByNameIgnoreCaseAndIdNot(String name, Long id);
    boolean existsById(Long id);
    Place findById(Long id);
    List<Place> findOrderedByRatingDesc();
    Place save(Place place);
    void removeById(Long id);
}
