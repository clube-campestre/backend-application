package com.campestre.clube.backend_application.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity (name = "transport")
public class Transport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String enterprise;
    private Double price;
    private Float travelDistance;
    private Integer capacity;
    private String company_contact;
    private String driver_contact;
    private Integer rating;

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

    public String getCompany_contact() {
        return company_contact;
    }

    public void setCompany_contact(String company_contact) {
        this.company_contact = company_contact;
    }

    public String getDriver_contact() {
        return driver_contact;
    }

    public void setDriver_contact(String driver_contact) {
        this.driver_contact = driver_contact;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
