package com.campestre.clube.backend_application.controller.dtos.responses;

public class TransportResponseDto {
    private Integer id;
    private String enterprise;
    private Double price;
    private Float travelDistance;
    private Integer capacity;
    private String companyContact;
    private String driverContact;
    private Integer rating;

    public TransportResponseDto(Integer id, String enterprise, Double price, Float travelDistance, Integer capacity, String companyContact, String driverContact, Integer rating) {
        this.id = id;
        this.enterprise = enterprise;
        this.price = price;
        this.travelDistance = travelDistance;
        this.capacity = capacity;
        this.companyContact = companyContact;
        this.driverContact = driverContact;
        this.rating = rating;
    }

    public TransportResponseDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(String enterprise) {
        this.enterprise = enterprise;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Float getTravelDistance() {
        return travelDistance;
    }

    public void setTravelDistance(Float travelDistance) {
        this.travelDistance = travelDistance;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getCompanyContact() {
        return companyContact;
    }

    public void setCompanyContact(String companyContact) {
        this.companyContact = companyContact;
    }

    public String getDriverContact() {
        return driverContact;
    }

    public void setDriverContact(String driverContact) {
        this.driverContact = driverContact;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
