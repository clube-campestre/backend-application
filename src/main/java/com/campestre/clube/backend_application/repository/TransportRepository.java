package com.campestre.clube.backend_application.repository;

import com.campestre.clube.backend_application.entity.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransportRepository extends JpaRepository<Transport, Integer> {
    boolean existsTransportByEnterpriseContainsIgnoreCase(String enterprise);
    boolean existsTransportByEnterpriseAndIdNot(String enterprise, Integer id);
    List<Transport> findAllByOrderByRatingDesc();
}
