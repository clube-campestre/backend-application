package com.campestre.clube.backend_application.controller.dtos.requests;

import com.campestre.clube.backend_application.entity.Address;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

public class SaveAddressRequestDto {

    @NotNull
    private Integer houseNumber;
    private String district;
    @NotNull
    private String city;
    @NotNull
    private String state;
    @NotNull
    private String cep;
    private String referenceHouse;


    public static Address toEntity(SaveAddressRequestDto dto){
        Address address = new Address();
        address.setHouseNumber(dto.houseNumber);
        address.setDistrict(dto.district);
        address.setCity(dto.city);
        address.setState(dto.state);
        address.setCep(dto.cep);
        address.setReferenceHouse(dto.referenceHouse);

        return address;

    }

    public SaveAddressRequestDto() {
    }

    public @NotNull Integer getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(@NotNull Integer houseNumber) {
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
