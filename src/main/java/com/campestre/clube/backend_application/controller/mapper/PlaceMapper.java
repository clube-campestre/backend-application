package com.campestre.clube.backend_application.controller.mapper;

import com.campestre.clube.backend_application.controller.dtos.requests.SavePlaceRequestDto;
import com.campestre.clube.backend_application.controller.dtos.requests.UpdatePlaceRequestDto;
import com.campestre.clube.backend_application.controller.dtos.responses.PlaceResponseDto;
import com.campestre.clube.backend_application.entity.Place;

import java.util.List;

public abstract class PlaceMapper {
    public static Place toEntity(SavePlaceRequestDto dto) {
        return new Place(
                AddressMapper.toEntity(dto.getAddress()), dto.getName(), dto.getPrice(), dto.getCapacity(),
                dto.getContact(), dto.getRating()
        );
    }

    public static Place toEntity(UpdatePlaceRequestDto dto) {
        return new Place(
                AddressMapper.toEntity(dto.getAddress()), dto.getName(), dto.getPrice(), dto.getCapacity(),
                dto.getContact(), dto.getRating()
        );
    }

    public static PlaceResponseDto toResponse(Place place) {
        return new PlaceResponseDto(
                place.getId(), place.getName(), place.getPrice(), place.getCapacity(), place.getContact(),
                place.getRating(), AddressMapper.toResponse(place.getAddress())
        );
    }

    public static List<PlaceResponseDto> toResponse(List<Place> places) {
        return places.stream().map(PlaceMapper::toResponse).toList();
    }
}
