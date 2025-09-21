package com.campestre.clube.backend_application.infrastructure.web.mappers;

import com.campestre.clube.backend_application.core.application.transport.command.SaveTransportCommand;
import com.campestre.clube.backend_application.core.application.transport.command.UpdateTransportCommand;
import com.campestre.clube.backend_application.core.domain.Transport;
import com.campestre.clube.backend_application.infrastructure.web.dtos.transport.SaveTransportRequestDto;
import com.campestre.clube.backend_application.infrastructure.web.dtos.transport.UpdateTransportRequestDto;
import com.campestre.clube.backend_application.infrastructure.web.dtos.transport.TransportResponseDto;

import java.util.List;
import java.util.stream.Collectors;

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

    public static UpdateTransportCommand toCommand(UpdateTransportRequestDto dto, Integer id) {
        return new UpdateTransportCommand(
                id,
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

    public static List<TransportResponseDto> toResponse(List<Transport> transports) {
        return transports.stream().map(TransportDtoMapper::toResponse).collect(Collectors.toList());
    }
}