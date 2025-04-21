package com.campestre.clube.backend_application.repository;

import com.campestre.clube.backend_application.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Integer> {
    boolean existsBySirname(String sirname);
    List<Place> findAllByOrderByRatingDesc();
}
