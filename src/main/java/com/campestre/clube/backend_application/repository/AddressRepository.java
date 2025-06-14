package com.campestre.clube.backend_application.repository;


import com.campestre.clube.backend_application.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    Address findByCep(String cep);

    boolean existsByStreetAndHouseNumberAndDistrictAndStateAndCityAndCepAndReferenceHouse(String street, String houseNumber, String district, String state, String city, String cep, String referenceHouse);
    Address findByStreetAndHouseNumberAndDistrictAndStateAndCityAndCepAndReferenceHouse(String street, String houseNumber, String district, String state, String city, String cep, String referenceHouse);
}
