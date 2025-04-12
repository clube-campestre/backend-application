package com.campestre.clube.backend_application.mapper;

import com.campestre.clube.backend_application.controller.dtos.requests.SaveAddressRequestDto;
import com.campestre.clube.backend_application.entity.Address;
import jakarta.validation.Valid;

public class AddressMapper {

    public static Address toEntity(@Valid SaveAddressRequestDto dto){
        Address address = new Address();
        address.setHouseNumber(dto.getHouseNumber());
        address.setDistrict(dto.getDistrict());
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        address.setCep(dto.getCep());
        address.setReferenceHouse(dto.getReferenceHouse());
        return address;
    }



}
