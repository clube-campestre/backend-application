package com.campestre.clube.backend_application.core.adapter;

import com.campestre.clube.backend_application.core.domain.Address;
import com.campestre.clube.backend_application.core.domain.Transport;
import com.campestre.clube.backend_application.core.domain.valueobject.Contact;

import java.util.List;

public interface AddressGateway {
    boolean existsById(Integer id);
    Address findById(Integer id);
    Address save(Address address);
    void removeById(Integer id);
}
