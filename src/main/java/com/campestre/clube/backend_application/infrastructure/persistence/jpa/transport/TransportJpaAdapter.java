package com.campestre.clube.backend_application.infrastructure.persistence.jpa.transport;

import com.campestre.clube.backend_application.core.adapter.TransportGateway;
import com.campestre.clube.backend_application.core.domain.Transport;
import com.campestre.clube.backend_application.core.domain.valueobject.Contact;
import org.springframework.stereotype.Repository;

@Repository
public class TransportJpaAdapter implements TransportGateway {

    private final TransportJpaRepository repository;

    public TransportJpaAdapter(TransportJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean existsTransportByCompanyIgnoreCaseAndDriverIgnoreCase(Contact company, Contact driver) {
        return repository.existsTransportByCompanyNameContainsIgnoreCaseAndCompanyNumberAndDriverNameContainsIgnoreCaseAndDriverNumber(
                company.getName(), company.getCellphoneNumber().getNumber(),
                driver.getName(), driver.getCellphoneNumber().getNumber()
        );
    }

    @Override
    public Transport save(Transport domain) {
        return TransportEntityMapper.toDomain(repository.save(TransportEntityMapper.toEntity(domain)));
    }
}
