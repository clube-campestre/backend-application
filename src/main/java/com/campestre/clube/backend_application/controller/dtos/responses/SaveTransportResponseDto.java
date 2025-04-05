package com.campestre.clube.backend_application.controller.dtos.responses;

import com.campestre.clube.backend_application.entity.Transport;

public class SaveTransportResponseDto {
    private Integer id;
    private String enterprise;
    private Double price;
    private Float travelDistance;
    private Integer capacity;
    private String companyContact;
    private String driverContact;
    private Integer rating;

    public static SaveTransportResponseDto toResponse(Transport transport) {
        SaveTransportResponseDto response = new SaveTransportResponseDto();
        response.setId(transport.getId());
        response.setEnterprise(transport.getEnterprise());
        response.setPrice(transport.getPrice());
        response.setTravelDistance(transport.getTravelDistance());
        response.setCapacity(transport.getCapacity());
        response.setCompanyContact(transport.getCompanyContact());
        response.setDriverContact(transport.getDriverContact());
        response.setRating(transport.getRating());
        return response;
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
