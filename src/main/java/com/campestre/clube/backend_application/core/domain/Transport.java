package com.campestre.clube.backend_application.core.domain;

import com.campestre.clube.backend_application.core.domain.valueobject.Contact;

import java.math.BigDecimal;

public class Transport {
    private Integer id;
    private BigDecimal price;
    private Float travelDistance;
    private Integer capacity;
    private Contact company;
    private Contact driver;
    private Integer rating;

    private Transport(Integer id, BigDecimal price, Float travelDistance, Integer capacity, Contact company,
                      Contact driver, Integer rating) {
        this.id = id;
        this.price = price;
        this.travelDistance = travelDistance;
        this.capacity = capacity;
        this.company = company;
        this.driver = driver;
        this.rating = rating;
    }

    public static Transport of(Integer id, BigDecimal price, Float travelDistance, Integer capacity, String companyName,
                               String companyNumber, String driverName, String driverNumber, Integer rating) {
        return new Transport(
                id,
                price,
                travelDistance,
                capacity,
                Contact.of(companyName, companyNumber),
                Contact.of(driverName, driverNumber),
                rating
        );
    }

    public Integer getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Float getTravelDistance() {
        return travelDistance;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Contact getCompany() {
        return company;
    }

    public Contact getDriver() {
        return driver;
    }

    public Integer getRating() {
        return rating;
    }
}
