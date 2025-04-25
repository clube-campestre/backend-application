package com.campestre.clube.backend_application.controller.dtos.responses;

import com.campestre.clube.backend_application.entity.Address;

public class SaveAddressResponseDto {


    private Integer id;
    private String houseNumber;
    private String district;
    private String city;
    private String state;
    private String cep;
    private String referenceHouse;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
