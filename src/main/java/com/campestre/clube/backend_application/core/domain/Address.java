package com.campestre.clube.backend_application.core.domain;

import com.campestre.clube.backend_application.core.domain.valueobject.Cep;

public class Address {
    private Long id;
    private String street;
    private String houseNumber;
    private String district;
    private String state;
    private String city;
    private Cep cep;
    private String referenceHouse;

    private Address(
            Long id, String street, String houseNumber, String district, String state, String city, Cep cep,
            String referenceHouse
    ) {
        this.id = id;
        this.street = street;
        this.houseNumber = houseNumber;
        this.district = district;
        this.state = state;
        this.city = city;
        this.cep = cep;
        this.referenceHouse = referenceHouse;
    }

    public static Address of(
            Long id, String street, String houseNumber, String district, String state, String city, String cep,
            String referenceHouse
    ) {
        return new Address(
                id,
                street,
                houseNumber,
                district,
                state,
                city,
                Cep.of(cep),
                referenceHouse
        );
    }

    public static Address of(
            String street, String houseNumber, String district, String state, String city, String cep,
            String referenceHouse
    ) {
        return new Address(
                null,
                street,
                houseNumber,
                district,
                state,
                city,
                Cep.of(cep),
                referenceHouse
        );
    }

    public Long getId() {
        return id;
    }

    public String getStreet() {
        return street;
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

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public Cep getCep() {
        return cep;
    }

    public String getReferenceHouse() {
        return referenceHouse;
    }

    public void setReferenceHouse(String referenceHouse) {
        this.referenceHouse = referenceHouse;
    }
}
