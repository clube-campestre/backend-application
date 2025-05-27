package com.campestre.clube.backend_application.controller.dtos.requests;

import jakarta.validation.constraints.*;

public class SaveAddressRequestDto {
    @NotBlank
    private String houseNumber;
    @NotBlank
    @Size(min = 2)
    private String district;
    @NotBlank
    private String city;
    @NotBlank
    private String state;
    @NotBlank
    private String street;
    @NotBlank
    @Size(min = 8, max = 8)
    private String cep;
    private String referenceHouse;

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getReferenceHouse() {
        return referenceHouse;
    }

    public void setReferenceHouse(String referenceHouse) {
        this.referenceHouse = referenceHouse;
    }
}
