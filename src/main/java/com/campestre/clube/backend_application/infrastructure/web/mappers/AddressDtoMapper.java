package com.campestre.clube.backend_application.infrastructure.web.mappers;

import com.campestre.clube.backend_application.core.domain.Address;
import com.campestre.clube.backend_application.infrastructure.web.dtos.address.AddressResponseDto;

import java.util.List;
import java.util.stream.Collectors;

public class AddressDtoMapper {

    public static AddressResponseDto toResponse(Address address) {
        return new AddressResponseDto(
                address.getId(),
                address.getHouseNumber(),
                address.getDistrict(),
                address.getCity(),
                address.getState(),
                address.getStreet(),
                address.getCep().getNumber(),
                address.getReferenceHouse(),
                address.getComplement()
        );
    }

    public static List<AddressResponseDto> toResponse(List<Address> addresss) {
        return addresss.stream().map(AddressDtoMapper::toResponse).collect(Collectors.toList());
    }
}