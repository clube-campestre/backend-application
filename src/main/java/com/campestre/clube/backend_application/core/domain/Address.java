package com.campestre.clube.backend_application.core.domain;

import com.campestre.clube.backend_application.core.domain.valueobject.Cep;

public class Address {
    private Integer id;
    private String street;
    private String houseNumber;
    private String district;
    private String state;
    private String city;
    private Cep cep;
    private String referenceHouse;

    private Address(
            Integer id, String street, String houseNumber, String district, String state, String city, Cep cep,
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
            Integer id, String street, String houseNumber, String district, String state, String city, String cep,
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
}
