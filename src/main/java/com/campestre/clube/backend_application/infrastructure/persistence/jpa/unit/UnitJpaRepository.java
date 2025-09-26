package com.campestre.clube.backend_application.infrastructure.persistence.jpa.unit;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UnitJpaRepository extends JpaRepository<UnitEntity, Integer> {
    Optional<UnitEntity> findBySurnameIgnoreCase(String unidade);
    Boolean existsBySurnameIgnoreCase(String unidade);
    List<UnitEntity> findAllByOrderByScoreDesc();
}
