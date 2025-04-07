package com.campestre.clube.backend_application.repository;

import com.campestre.clube.backend_application.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Integer> {
}
