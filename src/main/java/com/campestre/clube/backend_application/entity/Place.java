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
    private String contact;
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

