package com.campestre.clube.backend_application.service;

import com.campestre.clube.backend_application.entity.Address;
import com.campestre.clube.backend_application.exceptions.NotFoundException;
import com.campestre.clube.backend_application.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public Address saveIfNotExist(Address address) {
        if (addressRepository.existsByStreetAndHouseNumberAndDistrictAndStateAndCityAndCepAndReferenceHouse(
                address.getStreet(),
                address.getHouseNumber(),
                address.getDistrict(),
                address.getState(),
                address.getCity(),
                address.getCep(),
                address.getReferenceHouse()
        ))
            return addressRepository.findByCep(address.getCep());

        return addressRepository.save(address);
    }

    public Address update(Integer id, Address newAddress) {
        newAddress.setId(id);
        return addressRepository.save(newAddress);
    }

    public List<Address> getAll(){
        return addressRepository.findAll();
    }

    public Address getById(Integer id){
        Optional<Address> address = addressRepository.findById(id);
        if(address.isEmpty()){
            throw new NotFoundException("Address with id [%s] not found".formatted(id));
        }
        return address.get();
    }

    public Address getByCep(String cep){
        Address address = addressRepository.findByCep(cep);
        if(address == null) throw new NotFoundException("Address with CEP [%s] not found".formatted(cep));
        return address;
    }

    public Boolean existById(Integer id){
        return addressRepository.findById(id).isPresent();
    }

    public void delete(Integer id){
        if(!addressRepository.existsById(id)){
            throw new NotFoundException("Address with id [%s] not found".formatted(id));
        }
        addressRepository.deleteById(id);
    }

    public Boolean addressAlreadyExists(Address address){
        return addressRepository.existsByStreetAndHouseNumberAndDistrictAndStateAndCityAndCepAndReferenceHouse(
                address.getStreet(),
                address.getHouseNumber(),
                address.getDistrict(),
                address.getState(),
                address.getCity(),
                address.getCep(),
                address.getReferenceHouse()
        );
    }

}
