package com.campestre.clube.backend_application.infrastructure.web.mappers;

import com.campestre.clube.backend_application.core.application.place.command.SavePlaceCommand;
import com.campestre.clube.backend_application.core.application.place.command.UpdatePlaceCommand;
import com.campestre.clube.backend_application.core.domain.Place;
import com.campestre.clube.backend_application.infrastructure.web.dtos.request.SavePlaceRequestDto;
import com.campestre.clube.backend_application.infrastructure.web.dtos.request.UpdatePlaceRequestDto;
import com.campestre.clube.backend_application.infrastructure.web.dtos.response.PlaceResponseDto;

import java.util.List;
import java.util.stream.Collectors;

public class PlaceDtoMapper {

    public static SavePlaceCommand toCommand(SavePlaceRequestDto dto) {
        return new SavePlaceCommand(
                dto.getName(),
                dto.getPrice(),
                dto.getCapacity(),
                dto.getContactName(),
                dto.getContactCellphoneNumber(),
                dto.getRating(),
                dto.getAddress().getStreet(),
                dto.getAddress().getHouseNumber(),
                dto.getAddress().getDistrict(),
                dto.getAddress().getState(),
                dto.getAddress().getCity(),
                dto.getAddress().getCep(),
                dto.getAddress().getReferenceHouse()
        );
    }

    public static UpdatePlaceCommand toCommand(UpdatePlaceRequestDto dto, Integer id) {
        return new UpdatePlaceCommand(
                id,
                dto.getName(),
                dto.getPrice(),
                dto.getCapacity(),
                dto.getContactName(),
                dto.getContactCellphoneNumber(),
                dto.getRating(),
                dto.getAddress().getId(),
                dto.getAddress().getStreet(),
                dto.getAddress().getHouseNumber(),
                dto.getAddress().getDistrict(),
                dto.getAddress().getState(),
                dto.getAddress().getCity(),
                dto.getAddress().getCep(),
                dto.getAddress().getReferenceHouse()
        );
    }

    public static PlaceResponseDto toResponse(Place place) {
        return new PlaceResponseDto(
                place.getId(),
                place.getName(),
                place.getPrice(),
                place.getCapacity(),
                place.getContact().getName(),
                place.getContact().getCellphoneNumber().getNumber(),
                place.getRating(),
                AddressDtoMapper.toResponse(place.getAddress())
        );
    }

    public static List<PlaceResponseDto> toResponse(List<Place> places) {
        return places.stream().map(PlaceDtoMapper::toResponse).collect(Collectors.toList());
    }
}