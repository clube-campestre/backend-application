package com.campestre.clube.backend_application.core.adapter;

import com.campestre.clube.backend_application.core.domain.Transport;
import com.campestre.clube.backend_application.core.domain.valueobject.Contact;

public interface TransportGateway {
    boolean existsTransportByCompanyIgnoreCaseAndDriverIgnoreCase(Contact company, Contact driver);
    Transport save(Transport transport);
}
