package com.campestre.clube.backend_application.entity;

import jakarta.persistence.*;

@Entity(name = "place")
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    @JoinColumn(name = "fk_address")
    private Address address;
    @Column(name = "place_name")
    private String name;
    private Double price;
    private Integer capacity;
    private String contact;
    private Integer rating;

    public Place(Integer id, Address address, String name, Double price, Integer capacity, String contact, Integer rating) {
        this.id = id;
        this.address = address;
        this.name = name;
        this.price = price;
        this.capacity = capacity;
        this.contact = contact;
        this.rating = rating;
    }
  
    public Place(Address address, String name, Double price, Integer capacity, String contact, Integer rating) {
        this.address = address;
        this.name = name;
        this.price = price;
        this.capacity = capacity;
        this.contact = contact;
        this.rating = rating;
    }

    public Place() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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
}

