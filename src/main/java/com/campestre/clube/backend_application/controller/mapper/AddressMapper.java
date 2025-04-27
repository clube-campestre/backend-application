package com.campestre.clube.backend_application.controller.mapper;

import com.campestre.clube.backend_application.controller.dtos.requests.SaveAddressRequestDto;
import com.campestre.clube.backend_application.controller.dtos.responses.AddressResponseDto;
import com.campestre.clube.backend_application.entity.Address;

public class AddressMapper {
    public static Address toEntity(SaveAddressRequestDto dto){
        Address address = new Address();
        address.setHouseNumber(dto.getHouseNumber());
        address.setDistrict(dto.getDistrict());
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        address.setCep(dto.getCep());
        address.setReferenceHouse(dto.getReferenceHouse());

        return address;
    }

    public static AddressResponseDto toResponse(Address address){
        AddressResponseDto response = new AddressResponseDto();
        response.setId(address.getId());
        response.setHouseNumber(address.getHouseNumber());
        response.setDistrict(address.getDistrict());
        response.setCity(address.getCity());
        response.setCity(address.getCity());
        response.setState(address.getState());
        response.setCep(address.getCep());
        response.setReferenceHouse(address.getReferenceHouse());

        return response;
    }
}
