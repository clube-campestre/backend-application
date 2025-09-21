package com.campestre.clube.backend_application.infrastructure.web.dtos.transport;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class SaveTransportRequestDto {
    @NotBlank
    private String enterprise;
    @Positive @NotNull
    private BigDecimal price;
    @Positive @NotNull
    private Float travelDistance;
    @Positive @NotNull
    private Integer capacity;
    @NotBlank
    private String companyName;
    @NotBlank
    private String companyNumber;
    @NotBlank
    private String driverName;
    @NotBlank
    private String driverNumber;
    @NotNull @Min(1) @Max(5)
    private Integer rating;

    public String getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(String enterprise) {
        this.enterprise = enterprise;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyNumber() {
        return companyNumber;
    }

    public void setCompanyNumber(String companyNumber) {
        this.companyNumber = companyNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverNumber() {
        return driverNumber;
    }

    public void setDriverNumber(String driverNumber) {
        this.driverNumber = driverNumber;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
