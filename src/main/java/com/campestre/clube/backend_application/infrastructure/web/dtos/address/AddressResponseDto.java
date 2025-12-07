package com.campestre.clube.backend_application.infrastructure.web.dtos.address;

public class AddressResponseDto {
    private Long id;
    private String houseNumber;
    private String district;
    private String city;
    private String state;
    private String street;
    private String cep;
    private String referenceHouse;
    private String complement;

    public AddressResponseDto(
            Long id, String houseNumber, String district, String city, String state, String street, String cep,
            String referenceHouse, String complement
    ) {
        this.id = id;
        this.houseNumber = houseNumber;
        this.district = district;
        this.city = city;
        this.state = state;
        this.street = street;
        this.cep = cep;
        this.referenceHouse = referenceHouse;
        this.complement = complement;
    }

    public AddressResponseDto() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }
}
