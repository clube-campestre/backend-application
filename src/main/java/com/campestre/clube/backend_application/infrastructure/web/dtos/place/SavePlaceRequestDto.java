package com.campestre.clube.backend_application.infrastructure.web.dtos.place;

import com.campestre.clube.backend_application.infrastructure.web.dtos.address.SaveAddressRequestDto;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class SavePlaceRequestDto {
    @NotBlank
    @Size(max = 45)
    private String name;
    @NotNull
    private BigDecimal price;
    @NotNull
    private Integer capacity;
    @NotBlank
    private String contactName;
    @NotBlank
    @Size(max = 12)
    private String contactCellphoneNumber;
    @NotNull
    @Min(1)
    @Max(5)
    private Integer rating;
    @NotNull
    private SaveAddressRequestDto address;

    public SavePlaceRequestDto(String name, BigDecimal price, Integer capacity, String contactName, String contactCellphoneNumber, Integer rating, SaveAddressRequestDto address) {
        this.name = name;
        this.price = price;
        this.capacity = capacity;
        this.contactName = contactName;
        this.contactCellphoneNumber = contactCellphoneNumber;
        this.rating = rating;
        this.address = address;
    }

    public SavePlaceRequestDto() {}

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

    public SaveAddressRequestDto getAddress() {
        return address;
    }

    public void setAddress(SaveAddressRequestDto address) {
        this.address = address;
    }
}
