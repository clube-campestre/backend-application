package com.campestre.clube.backend_application.controller.mapper;

import com.campestre.clube.backend_application.controller.dtos.requests.SaveTransportRequestDto;
import com.campestre.clube.backend_application.controller.dtos.responses.TransportResponseDto;
import com.campestre.clube.backend_application.entity.Transport;

import java.util.List;

public abstract class TransportMapper {
    public static TransportResponseDto toResponse(Transport transport) {
        return new TransportResponseDto(
                transport.getId(), transport.getEnterprise(), transport.getPrice(), transport.getTravelDistance(),
                transport.getCapacity(), transport.getCompanyContact(), transport.getDriverContact(),
                transport.getRating()
        );
    }

    public static List<TransportResponseDto> toResponse(List<Transport> transports) {
        return transports.stream().map(TransportMapper::toResponse).toList();
    }

    public static Transport toEntity(SaveTransportRequestDto dto) {
        return new Transport(
                dto.getEnterprise(), dto.getPrice(), dto.getTravelDistance(), dto.getCapacity(),
                dto.getCompanyContact(), dto.getDriverContact(), dto.getRating()
        );
    }
}
