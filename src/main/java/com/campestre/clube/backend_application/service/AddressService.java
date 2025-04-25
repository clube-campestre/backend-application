package com.campestre.clube.backend_application.service;

import com.campestre.clube.backend_application.controller.dtos.requests.MemberDataDtoRequest;
import com.campestre.clube.backend_application.controller.dtos.requests.SaveAddressRequestDto;
import com.campestre.clube.backend_application.controller.mapper.AddressMapper;
import com.campestre.clube.backend_application.entity.Address;
import com.campestre.clube.backend_application.exceptions.NotFoundException;
import com.campestre.clube.backend_application.repository.AddressRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public Address createAddress(SaveAddressRequestDto dto) {
        Address address = AddressMapper.toEntity(dto);

        // Se já existe um endereço com esse CEP, use o existente
        if (addressRepository.existsByCep(address.getCep())) {
            return addressRepository.findByCep(address.getCep());
        }
        return address;

    }

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

    public Address update(Integer id, SaveAddressRequestDto dto){
        Address newAddress = AddressMapper.toEntity(dto);
        if(!addressRepository.existsById(id)){
            throw new NotFoundException("Address with id [%s] not found".formatted(id));
        }
        newAddress.setId(id);
        return newAddress;
    }


    public void delete(Integer id){
        if(!addressRepository.existsById(id)){
            throw new NotFoundException("Address with id [%s] not found".formatted(id));
        }
        addressRepository.deleteById(id);
    }

    public Boolean addressAlreadyExists(String cep){
        return addressRepository.existsByCep(cep);
    }

}
