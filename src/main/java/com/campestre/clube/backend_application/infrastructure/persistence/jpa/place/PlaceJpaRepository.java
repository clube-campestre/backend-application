package com.campestre.clube.backend_application.infrastructure.persistence.jpa.place;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceJpaRepository extends JpaRepository<PlaceEntity, Long> {
    boolean existsByNameIgnoreCase(String name);
    boolean existsByNameIgnoreCaseAndIdNot(String name, Long id);

    List<PlaceEntity> findAllByOrderByRatingDesc();
}
