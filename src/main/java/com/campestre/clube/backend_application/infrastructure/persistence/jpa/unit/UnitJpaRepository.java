package com.campestre.clube.backend_application.infrastructure.persistence.jpa.unit;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UnitJpaRepository extends JpaRepository<UnitEntity, Long> {
    Boolean existsBySurnameIgnoreCase(String unidade);

    Optional<UnitEntity> findBySurnameIgnoreCase(String unidade);
    List<UnitEntity> findByScoreNot(Integer score);
    List<UnitEntity> findAllByOrderByScoreDesc();
}
