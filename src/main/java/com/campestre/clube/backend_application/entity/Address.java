package com.campestre.clube.backend_application.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String street;
    private String houseNumber;
    private String district;
    private String state;
    private String city;
    private String cep;
    private String referenceHouse;

    public Address(Integer id, String street, String houseNumber, String district, String state, String city, String cep, String referenceHouse) {
        this.id = id;
        this.street = street;
        this.houseNumber = houseNumber;
        this.district = district;
        this.state = state;
        this.city = city;
        this.cep = cep;
        this.referenceHouse = referenceHouse;
    }

    public Address(String street, String houseNumber, String district, String state, String city, String cep, String referenceHouse) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.district = district;
        this.state = state;
        this.city = city;
        this.cep = cep;
        this.referenceHouse = referenceHouse;
    }
  
    public Address() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
