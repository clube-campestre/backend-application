package com.campestre.clube.backend_application.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity(name = "place")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "fk_address")
    private Integer fkAddress;
    @Size(max = 45) @NotBlank
    private String sirname;
    @NotBlank
    private Double price;
    @NotBlank
    private Integer capacity;
    @NotBlank @Size(max = 12)
    private Integer contact;
    @NotBlank @Size(min = 1, max = 10)
    private Integer rating;


    public Integer getId() {
        return id;
    }

    public Integer getFkAddress() {
        return fkAddress;
    }

    public void setFkAddress(Integer fkAddress) {
        this.fkAddress = fkAddress;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @Size(max = 45) @NotBlank String getSirname() {
        return sirname;
    }

    public void setSirname(@Size(max = 45) @NotBlank String sirname) {
        this.sirname = sirname;
    }

    public @NotBlank Double getPrice() {
        return price;
    }

    public void setPrice(@NotBlank Double price) {
        this.price = price;
    }

    public @NotBlank Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(@NotBlank Integer capacity) {
        this.capacity = capacity;
    }

    public @NotBlank @Size(max = 12) Integer getContact() {
        return contact;
    }

    public void setContact(@NotBlank @Size(max = 12) Integer contact) {
        this.contact = contact;
    }

    public @NotBlank @Size(min = 1, max = 10) Integer getRating() {
        return rating;
    }

    public void setRating(@NotBlank @Size(min = 1, max = 10) Integer rating) {
        this.rating = rating;
    }
}

