package com.campestre.clube.backend_application.controller.dtos.responses;

public class GetPlaceResponseDto {

    private Integer id;
    private String sirname;
    private Double price;
    private Integer capacity;
    private String contact;
    private Integer rating;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSirname() {
        return sirname;
    }

    public void setSirname(String sirname) {
        this.sirname = sirname;
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
}
