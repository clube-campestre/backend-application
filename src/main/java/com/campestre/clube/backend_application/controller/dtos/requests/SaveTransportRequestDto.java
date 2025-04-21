package com.campestre.clube.backend_application.controller.dtos.requests;

import jakarta.validation.constraints.*;

public class SaveTransportRequestDto {
    @NotBlank
    private String enterprise;
    @Positive
    @NotNull
    private Double price;
    @Positive
    @NotNull
    private Float travelDistance;
    @Positive
    @NotNull
    private Integer capacity;
    @NotBlank
    private String companyContact;
    @NotBlank
    private String driverContact;
    @NotNull
    @Min(1)
    @Max(10)
    private Integer rating;

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
