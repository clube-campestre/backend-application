package com.campestre.clube.backend_application.core.adapter;

import com.campestre.clube.backend_application.core.domain.Unit;

import java.util.List;

public interface UnitGateway {
    boolean existsBySurnameIgnoreCase(String surname);

    Unit findBySurnameIgnoreCase(String surname);
    List<Unit> findByScoreNot(Integer score);
    List<Unit> findOrderByScoreDesc();

    Unit save(Unit unit);
}
