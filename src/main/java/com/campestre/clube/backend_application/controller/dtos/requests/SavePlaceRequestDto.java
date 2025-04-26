package com.campestre.clube.backend_application.controller.dtos.requests;

import jakarta.validation.constraints.*;

public class SavePlaceRequestDto {
    @NotBlank
    @Size(max = 45)
    private String name;
    @NotNull
    private Double price;
    @NotNull
    private Integer capacity;
    @NotBlank
    @Size(max = 12)
    private String contact;
    @NotNull
    @Min(1)
    @Max(5)
    private Integer rating;
    @NotNull
    private SaveAddressRequestDto address;

    public SaveAddressRequestDto getAddress() {
        return address;
    }

    public void setAddress(SaveAddressRequestDto address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
