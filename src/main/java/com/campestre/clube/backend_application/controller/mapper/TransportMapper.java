package com.campestre.clube.backend_application.controller.mapper;

import com.campestre.clube.backend_application.controller.dtos.requests.SaveTransportRequestDto;
import com.campestre.clube.backend_application.controller.dtos.responses.TransportResponseDto;
import com.campestre.clube.backend_application.entity.Transport;

public abstract class TransportMapper {
    public static TransportResponseDto toResponse(Transport transport) {
        TransportResponseDto response = new TransportResponseDto();
        response.setId(transport.getId());
        response.setEnterprise(transport.getEnterprise());
        response.setPrice(transport.getPrice());
        response.setTravelDistance(transport.getTravelDistance());
        response.setCapacity(transport.getCapacity());
        response.setCompanyContact(transport.getCompanyContact());
        response.setDriverContact(transport.getDriverContact());
        response.setRating(transport.getRating());
        return response;
    }

    public static Transport toEntity(SaveTransportRequestDto dto) {
        Transport transport = new Transport();
        transport.setEnterprise(dto.getEnterprise());
        transport.setPrice(dto.getPrice());
        transport.setTravelDistance(dto.getTravelDistance());
        transport.setCapacity(dto.getCapacity());
        transport.setCompanyContact(dto.getCompanyContact());
        transport.setDriverContact(dto.getDriverContact());
        transport.setRating(dto.getRating());
        return transport;
    }
}
