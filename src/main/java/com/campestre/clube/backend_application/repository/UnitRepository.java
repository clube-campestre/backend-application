package com.campestre.clube.backend_application.repository;

import com.campestre.clube.backend_application.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UnitRepository extends JpaRepository<Unit, Integer> {
    Optional<Unit> findBySurname(String unidade);
    Optional<Unit> findById(Integer id);
    Integer findScoreById(Integer id);
}
