package com.campestre.clube.backend_application.controller.dtos.requests;

import com.campestre.clube.backend_application.entity.Address;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

public class SaveAddressRequestDto {

    @NotNull
    private String houseNumber;
    private String district;
    @NotNull
    private String city;
    @NotNull
    private String state;
    @NotNull
    private String cep;
    private String referenceHouse;


    public SaveAddressRequestDto() {
    }

    public @NotNull String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(@NotNull String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public @NotNull String getCity() {
        return city;
    }

    public void setCity(@NotNull String city) {
        this.city = city;
    }

    public @NotNull String getState() {
        return state;
    }

    public void setState(@NotNull String state) {
        this.state = state;
    }

    public @NotNull String getCep() {
        return cep;
    }

    public void setCep(@NotNull String cep) {
        this.cep = cep;
    }

    public String getReferenceHouse() {
        return referenceHouse;
    }

    public void setReferenceHouse(String referenceHouse) {
        this.referenceHouse = referenceHouse;
    }
}
