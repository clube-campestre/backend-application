package com.campestre.clube.backend_application.controller.mapper;

import com.campestre.clube.backend_application.controller.dtos.requests.SavePlaceRequestDto;
import com.campestre.clube.backend_application.controller.dtos.responses.GetPlaceResponseDto;
import com.campestre.clube.backend_application.controller.dtos.responses.SavePlaceResponseDto;
import com.campestre.clube.backend_application.entity.Address;
import com.campestre.clube.backend_application.entity.Place;

public abstract class PlaceMapper {
    public static Place toEntity(SavePlaceRequestDto dto) {
        Place place = new Place();
        place.setFkAddress(dto.getFkAddress());
        place.setSirname(dto.getSirname());
        place.setPrice(dto.getPrice());
        place.setCapacity(dto.getCapacity());
        place.setContact(dto.getContact());
        place.setRating(dto.getRating());
        return place;
    }

    public static SavePlaceResponseDto toSaveResponse(Place place, Address address) {
        SavePlaceResponseDto response = new SavePlaceResponseDto();
        response.setId(place.getId());
        response.setSirname(place.getSirname());
        response.setPrice(place.getPrice());
        response.setCapacity(place.getCapacity());
        response.setContact(place.getContact());
        response.setRating(place.getRating());
        response.setAddressId(place.getFkAddress());
        response.setAddress(place.getFkAddress());
        return response;
    }

    public static GetPlaceResponseDto toGetResponse(Place place, Address address) {
        GetPlaceResponseDto response = new GetPlaceResponseDto();
        response.setId(place.getId());
        response.setSirname(place.getSirname());
        response.setPrice(place.getPrice());
        response.setCapacity(place.getCapacity());
        response.setContact(place.getContact());
        response.setRating(place.getRating());
        response.setAddressId(place.getFkAddress());
        response.setAddress(address);
        return response;
    }
}
