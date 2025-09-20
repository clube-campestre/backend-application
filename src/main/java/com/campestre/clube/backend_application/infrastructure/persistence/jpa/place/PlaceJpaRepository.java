package com.campestre.clube.backend_application.infrastructure.persistence.jpa.place;

import com.campestre.clube.backend_application.core.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceJpaRepository extends JpaRepository<PlaceEntity, Integer> {
    boolean existsByNameIgnoreCase(String name);
    boolean existsByNameIgnoreCaseAndIdNot(String name, Integer id);
    boolean existsByAddress(Address address);
    boolean existsByAddressAndIdNot(Address address, Integer id);
    List<PlaceEntity> findAllByOrderByRatingDesc();
}
