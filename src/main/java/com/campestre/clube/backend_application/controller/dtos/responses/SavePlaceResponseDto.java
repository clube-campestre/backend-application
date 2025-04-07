package com.campestre.clube.backend_application.controller.dtos.responses;

import com.campestre.clube.backend_application.entity.Place;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SavePlaceResponseDto {

    private Long id;
    private String sirname;
    private Float price;
    private Integer capacity;
    private String contact;
    private Integer rating;



    private static SavePlaceResponseDto toResponse(Place place) {
        SavePlaceResponseDto response = new SavePlaceResponseDto();

                response.setId(place.getId());
                response.setSirname(place.getSirname());
                response.setPrice(place.getPrice());
                response.setCapacity(place.getCapacity());
                response.setContact(place.getContact());
                response.setRating(place.getRating());

                return response;


    }

    private void setRating(@NotBlank @Size(min = 1, max = 10) Integer rating) {
    }

    private void setContact(@NotBlank @Size(max = 12) Integer contact) {
    }

    private void setCapacity(@NotBlank Integer capacity) {
    }

    private void setPrice(@NotBlank Double price) {
    }

    private void setSirname(@Size(max = 45) @NotBlank String sirname) {
    }

    private void setId(Integer id) {
    }


}
