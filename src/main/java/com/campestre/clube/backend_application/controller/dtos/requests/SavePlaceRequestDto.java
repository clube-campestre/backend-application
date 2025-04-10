package com.campestre.clube.backend_application.controller.dtos.requests;

import com.campestre.clube.backend_application.entity.Place;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SavePlaceRequestDto {
    @NotNull
    private Integer fkAddress;
    @NotBlank
    @Size(max = 45)
    private String sirname;
    @NotNull
    private Double price;
    @NotNull
    private Integer capacity;
    @NotNull
    @Size(max = 12)
    private String contact;
    @NotNull
    @Size(min = 1, max = 10)
    private Integer rating;

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


    public @NotNull Integer getFkAddress() {
        return fkAddress;
    }

    public void setFkAddress(@NotNull Integer fkAddress) {
        this.fkAddress = fkAddress;
    }

    public @NotBlank @Size(max = 45) String getSirname() {
        return sirname;
    }

    public void setSirname(@NotBlank @Size(max = 45) String sirname) {
        this.sirname = sirname;
    }

    public @NotNull Double getPrice() {
        return price;
    }

    public void setPrice(@NotNull Double price) {
        this.price = price;
    }

    public @NotNull Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(@NotNull Integer capacity) {
        this.capacity = capacity;
    }

    public @NotNull @Size(max = 12) String getContact() {
        return contact;
    }

    public void setContact(@NotNull @Size(max = 12) String contact) {
        this.contact = contact;
    }

    public @NotNull @Size(min = 1, max = 10) Integer getRating() {
        return rating;
    }

    public void setRating(@NotNull @Size(min = 1, max = 10) Integer rating) {
        this.rating = rating;
    }
}
