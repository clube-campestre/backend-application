package com.campestre.clube.backend_application.infrastructure.persistence.jpa.transport;

import com.campestre.clube.backend_application.core.adapter.TransportGateway;
import com.campestre.clube.backend_application.core.domain.Transport;
import com.campestre.clube.backend_application.core.domain.valueobject.Contact;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransportJpaAdapter implements TransportGateway {

    private final TransportJpaRepository repository;

    public TransportJpaAdapter(TransportJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean existsByCompanyIgnoreCaseAndDriverIgnoreCase(Contact company, Contact driver) {
        return repository.existsByCompanyNameContainsIgnoreCaseAndCompanyNumberAndDriverNameContainsIgnoreCaseAndDriverNumber(
                company.getName(), company.getCellphoneNumber().getNumber(),
                driver.getName(), driver.getCellphoneNumber().getNumber()
        );
    }

    @Override
    public boolean existsByCompanyIgnoreCaseAndDriverIgnoreCaseAndIdNot(Contact company, Contact driver, Integer id) {
        return repository.existsByCompanyNameContainsIgnoreCaseAndCompanyNumberAndDriverNameContainsIgnoreCaseAndDriverNumberAndIdNot(
                company.getName(), company.getCellphoneNumber().getNumber(),
                driver.getName(), driver.getCellphoneNumber().getNumber(),
                id
        );
    }

    @Override
    public boolean existsById(Integer id) {
        return repository.existsById(id);
    }

    @Override
    public List<Transport> findOrderedByRatingDesc() {
        return TransportEntityMapper.toDomain(repository.findAllByOrderByRatingDesc());
    }

    @Override
    public Transport findById(Integer id) {
        return TransportEntityMapper.toDomain(repository.findById(id).get());
    }

    @Override
    public Transport save(Transport domain) {
        return TransportEntityMapper.toDomain(repository.save(TransportEntityMapper.toEntity(domain)));
    }

    @Override
    public void removeById(Integer id) {
        repository.deleteById(id);
    }
}
