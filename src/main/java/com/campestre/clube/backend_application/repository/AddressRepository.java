package com.campestre.clube.backend_application.repository;


import com.campestre.clube.backend_application.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    Optional<Address> findById(Integer id);

    boolean existsByCep(String cep);

    Address findByCep(String cep);
}
