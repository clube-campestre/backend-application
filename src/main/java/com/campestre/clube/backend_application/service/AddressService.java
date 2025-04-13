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

    public Address register(Address address){
        return addressRepository.save(address);
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

    public Address update(Integer id, Address newAddress){
        if(!addressRepository.existsById(id)){
            throw new NotFoundException("Address with id [%s] not found".formatted(id));
        }
        newAddress.setId(id);
        return addressRepository.save(newAddress);
    }


    public void delete(Integer id){
        if(!addressRepository.existsById(id)){
            throw new NotFoundException("Address with id [%s] not found".formatted(id));
        }
        addressRepository.deleteById(id);
    }

}
