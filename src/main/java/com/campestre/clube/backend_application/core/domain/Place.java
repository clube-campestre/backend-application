package com.campestre.clube.backend_application.core.domain;

import com.campestre.clube.backend_application.core.domain.valueobject.Contact;

import java.math.BigDecimal;

public class Place {
    private Integer id;
    private Address address;
    private String name;
    private BigDecimal price;
    private Integer capacity;
    private Contact contact;
    private Integer rating;

    private Place(
            Integer id, Address address, String name, BigDecimal price, Integer capacity, Contact contact,
            Integer rating
    ) {
        this.id = id;
        this.address = address;
        this.name = name;
        this.price = price;
        this.capacity = capacity;
        this.contact = contact;
        this.rating = rating;
    }

    public static Place of(
            Integer id, Address address, String name, BigDecimal price, Integer capacity, String contactName,
            String contactNumber, Integer rating
    ) {
        return new Place(
                id,
                address,
                name,
                price,
                capacity,
                Contact.of(contactName, contactNumber),
                rating
        );
    }

    public static Place of(
            Address address, String name, BigDecimal price, Integer capacity, String contactName, String contactNumber,
            Integer rating
    ) {
        return new Place(
                null,
                address,
                name,
                price,
                capacity,
                Contact.of(contactName, contactNumber),
                rating
        );
    }

    public Integer getId() {
        return id;
    }

    public Address getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Contact getContact() {
        return contact;
    }

    public Integer getRating() {
        return rating;
    }
}

