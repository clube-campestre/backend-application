package com.campestre.clube.backend_application.core.adapter;

import com.campestre.clube.backend_application.core.domain.Unit;
import com.campestre.clube.backend_application.core.domain.valueobject.Contact;

import java.util.List;

public interface UnitGateway {
    boolean existsById(Integer id);
    boolean existsBySurnameIgnoreCase(String surname);

    Unit findBySurnameIgnoreCase(String surname);
    List<Unit> findOrderByScoreDesc();
    Unit findById(Integer id);

    Unit save(Unit unit);

    void removeById(Integer id);
}
