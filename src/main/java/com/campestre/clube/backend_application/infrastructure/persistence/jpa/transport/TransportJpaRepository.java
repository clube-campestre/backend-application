package com.campestre.clube.backend_application.infrastructure.persistence.jpa.transport;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransportJpaRepository extends JpaRepository<TransportEntity, Integer> {
    boolean existsTransportByCompanyNameContainsIgnoreCaseAndCompanyNumberAndDriverNameContainsIgnoreCaseAndDriverNumber(String companyName, String companyNumber, String driverName, String driverNumber);
}
