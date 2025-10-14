package com.campestre.clube.backend_application.infrastructure.persistence.jpa.address;

import jakarta.persistence.*;

@Entity
@Table(name = "address")
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "street", nullable = false)
    private String street;
    @Column(name = "house_number", nullable = false)
    private String houseNumber;
    @Column(name = "district", nullable = false)
    private String district;
    @Column(name = "state", nullable = false)
    private String state;
    @Column(name = "city", nullable = false)
    private String city;
    @Column(name = "cep", nullable = false)
    private String cep;
    @Column(name = "reference_house", nullable = true)
    private String referenceHouse;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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