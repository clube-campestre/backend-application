package com.campestre.clube.backend_application.controller.dtos.responses;

public class PlaceResponseDto {
    private Integer id;
    private String name;
    private Double price;
    private Integer capacity;
    private String contact;
    private Integer rating;
    private AddressResponseDto address;

    public PlaceResponseDto(Integer id, String name, Double price, Integer capacity, String contact, Integer rating, AddressResponseDto address) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.capacity = capacity;
        this.contact = contact;
        this.rating = rating;
        this.address = address;
    }

    public PlaceResponseDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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
