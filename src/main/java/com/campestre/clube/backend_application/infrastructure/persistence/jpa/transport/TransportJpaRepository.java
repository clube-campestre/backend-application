package com.campestre.clube.backend_application.infrastructure.persistence.jpa.transport;

import com.campestre.clube.backend_application.core.domain.Transport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransportJpaRepository extends JpaRepository<TransportEntity, Integer> {
    boolean existsByCompanyNameContainsIgnoreCaseAndCompanyNumberAndDriverNameContainsIgnoreCaseAndDriverNumber(
            String companyName, String companyNumber, String driverName, String driverNumber
    );
    boolean existsByCompanyNameContainsIgnoreCaseAndCompanyNumberAndDriverNameContainsIgnoreCaseAndDriverNumberAndIdNot(
            String companyName, String companyNumber, String driverName, String driverNumber, Integer id
    );
    List<TransportEntity> findAllByOrderByRatingDesc();
}
