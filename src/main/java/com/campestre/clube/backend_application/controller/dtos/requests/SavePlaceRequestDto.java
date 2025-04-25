package com.campestre.clube.backend_application.controller.dtos.requests;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

public class SavePlaceRequestDto {
    //Entidade Place
    @NotBlank
    @Size(max = 45)
    private String sirname;
    @NotNull
    private Double price;
    @NotNull
    private Integer capacity;
    @NotBlank
    @Size(max = 12)
    private String contact;
    @NotNull
    @Min(1)
    @Max(10)
    private Integer rating;

    //Entidade Address
//    @NotNull
    private Integer fkAddress;

    @Valid
    private SaveAddressRequestDto address; //DTO do endereço


    public Integer getFkAddress() {
        return fkAddress;
    }

    public void setFkAddress(Integer fkAddress) {
        this.fkAddress = fkAddress;
    }

    public SaveAddressRequestDto getAddress() {
        return address;
    }

    public void setAddress(SaveAddressRequestDto address) {
        this.address = address;
    }

    public String getSirname() {
        return sirname;
    }

    public void setSirname(String sirname) {
        this.sirname = sirname;
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
