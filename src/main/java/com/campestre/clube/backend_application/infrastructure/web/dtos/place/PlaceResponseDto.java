package com.campestre.clube.backend_application.infrastructure.web.dtos.place;

import com.campestre.clube.backend_application.infrastructure.web.dtos.address.AddressResponseDto;

import java.math.BigDecimal;

public class PlaceResponseDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private Integer capacity;
    private String contactName;
    private String contactCellphoneNumber;
    private Integer rating;
    private AddressResponseDto address;

    public PlaceResponseDto(Long id, String name, BigDecimal price, Integer capacity, String contactName, String contactCellphoneNumber, Integer rating, AddressResponseDto address) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.capacity = capacity;
        this.contactName = contactName;
        this.contactCellphoneNumber = contactCellphoneNumber;
        this.rating = rating;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactCellphoneNumber() {
        return contactCellphoneNumber;
    }

    public void setContactCellphoneNumber(String contactCellphoneNumber) {
        this.contactCellphoneNumber = contactCellphoneNumber;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public AddressResponseDto getAddress() {
        return address;
    }

    public void setAddress(AddressResponseDto address) {
        this.address = address;
    }
}
