package com.campestre.clube.backend_application.core.adapter;

import com.campestre.clube.backend_application.core.domain.Address;
import com.campestre.clube.backend_application.core.domain.Place;

import java.util.List;

public interface PlaceGateway {
    boolean existsByNameIgnoreCase(String name);
    boolean existsByAddress(Address address);
    boolean existsByNameIgnoreCaseAndIdNot(String name, Integer id);
    boolean existsByAddressAndIdNot(Address address, Integer id);
    boolean existsById(Integer id);
    Place findById(Integer id);
    List<Place> findOrderedByRatingDesc();
    Place save(Place place);
    void removeById(Integer id);
}
