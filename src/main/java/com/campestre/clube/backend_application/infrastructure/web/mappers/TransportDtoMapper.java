package com.campestre.clube.backend_application.infrastructure.web.mappers;

import com.campestre.clube.backend_application.core.application.transport.command.SaveTransportCommand;
import com.campestre.clube.backend_application.core.domain.Transport;
import com.campestre.clube.backend_application.infrastructure.web.dtos.request.SaveTransportRequestDto;
import com.campestre.clube.backend_application.infrastructure.web.dtos.response.TransportResponseDto;

public class TransportDtoMapper {

    public static SaveTransportCommand toCommand(SaveTransportRequestDto dto) {
        return new SaveTransportCommand(
                dto.getPrice(),
                dto.getTravelDistance(),
                dto.getCapacity(),
                dto.getCompanyName(),
                dto.getCompanyNumber(),
                dto.getDriverName(),
                dto.getDriverNumber(),
                dto.getRating()
        );
    }

    public static TransportResponseDto toResponse(Transport transport) {
        return new TransportResponseDto(
                transport.getId(),
                transport.getPrice(),
                transport.getTravelDistance(),
                transport.getCapacity(),
                transport.getCompany().getName(),
                transport.getCompany().getCellphoneNumber().getNumber(),
                transport.getDriver().getName(),
                transport.getDriver().getCellphoneNumber().getNumber(),
                transport.getRating()
        );
    }
}