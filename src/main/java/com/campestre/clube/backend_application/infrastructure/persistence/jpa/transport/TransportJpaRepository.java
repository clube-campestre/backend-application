package com.campestre.clube.backend_application.infrastructure.persistence.jpa.transport;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransportJpaRepository extends JpaRepository<TransportEntity, Long> {
    boolean existsByCompanyNameContainsIgnoreCaseAndCompanyNumberAndDriverNameContainsIgnoreCaseAndDriverNumber(
            String companyName, String companyNumber, String driverName, String driverNumber
    );
    boolean existsByCompanyNameContainsIgnoreCaseAndCompanyNumberAndDriverNameContainsIgnoreCaseAndDriverNumberAndIdNot(
            String companyName, String companyNumber, String driverName, String driverNumber, Long id
    );
    List<TransportEntity> findAllByOrderByRatingDesc();
}
