package com.campestre.clube.backend_application.deprecated.controller.mapper;

import com.campestre.clube.backend_application.deprecated.controller.dtos.requests.SaveAddressRequestDto;
import com.campestre.clube.backend_application.deprecated.controller.dtos.requests.UpdateAddressRequestDto;
import com.campestre.clube.backend_application.deprecated.controller.dtos.responses.AddressResponseDto;
import com.campestre.clube.backend_application.deprecated.entity.Address;

public class AddressMapper {
    public static Address toEntity(SaveAddressRequestDto dto){
        return new Address(
                dto.getStreet(), dto.getHouseNumber(), dto.getDistrict(), dto.getState(), dto.getCity(), dto.getCep(),
                dto.getReferenceHouse()
        );
    }

    public static Address toEntity(UpdateAddressRequestDto dto){
        return new Address(
                dto.getId(), dto.getStreet(), dto.getHouseNumber(), dto.getDistrict(), dto.getState(), dto.getCity(),
                dto.getCep(), dto.getReferenceHouse()
        );
    }

    public static AddressResponseDto toResponse(Address address){
        return new AddressResponseDto(
                address.getId(), address.getHouseNumber(), address.getDistrict(), address.getCity(), address.getState(),
                address.getStreet(), address.getCep(), address.getReferenceHouse()
        );
    }
}
