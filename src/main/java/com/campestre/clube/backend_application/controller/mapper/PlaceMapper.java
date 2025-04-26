package com.campestre.clube.backend_application.controller.mapper;

import com.campestre.clube.backend_application.controller.dtos.requests.SavePlaceRequestDto;
import com.campestre.clube.backend_application.controller.dtos.responses.PlaceResponseDto;
import com.campestre.clube.backend_application.entity.Place;

public abstract class PlaceMapper {
    public static Place toEntity(SavePlaceRequestDto dto) {
        Place place = new Place();
        place.setAddress(AddressMapper.toEntity(dto.getAddress()));
        place.setName(dto.getName());
        place.setPrice(dto.getPrice());
        place.setCapacity(dto.getCapacity());
        place.setContact(dto.getContact());
        place.setRating(dto.getRating());
        return place;
    }

    public static PlaceResponseDto toResponse(Place place) {
        PlaceResponseDto response = new PlaceResponseDto();
        response.setId(place.getId());
        response.setName(place.getName());
        response.setPrice(place.getPrice());
        response.setCapacity(place.getCapacity());
        response.setContact(place.getContact());
        response.setRating(place.getRating());
        response.setAddress(AddressMapper.toResponse(place.getAddress()));
        return response;
    }
}
