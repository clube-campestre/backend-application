package com.campestre.clube.backend_application.core.adapter;

import com.campestre.clube.backend_application.core.domain.Transport;
import com.campestre.clube.backend_application.core.domain.valueobject.Contact;

import java.util.List;

public interface TransportGateway {
    boolean existsByCompanyIgnoreCaseAndDriverIgnoreCase(Contact company, Contact driver);
    boolean existsByCompanyIgnoreCaseAndDriverIgnoreCaseAndIdNot(Contact company, Contact driver, Integer id);
    boolean existsById(Integer id);
    List<Transport> findOrderedByRatingDesc();
    Transport findById(Integer id);
    Transport save(Transport transport);
    void removeById(Integer id);
}
