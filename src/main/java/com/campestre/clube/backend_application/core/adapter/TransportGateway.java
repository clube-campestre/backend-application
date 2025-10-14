package com.campestre.clube.backend_application.core.adapter;

import com.campestre.clube.backend_application.core.domain.Transport;
import com.campestre.clube.backend_application.core.domain.valueobject.Contact;

import java.util.List;

public interface TransportGateway {
    boolean existsByCompanyIgnoreCaseAndDriverIgnoreCase(Contact company, Contact driver);
    boolean existsByCompanyIgnoreCaseAndDriverIgnoreCaseAndIdNot(Contact company, Contact driver, Long id);
    boolean existsById(Long id);
    List<Transport> findOrderedByRatingDesc();
    Transport findById(Long id);
    Transport save(Transport transport);
    void removeById(Long id);
}
