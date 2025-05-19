package com.campestre.clube.backend_application.repository;

import com.campestre.clube.backend_application.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Integer> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Integer id);
    List<Place> findAllByOrderByRatingDesc();
}
