package com.campestre.clube.backend_application.infrastructure.persistence.jpa.place;

import com.campestre.clube.backend_application.infrastructure.persistence.jpa.address.AddressEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "place")
public class PlaceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "fk_address")
    private AddressEntity address;

    @Column(nullable = false, unique = true)
    private String name;

    private BigDecimal price;
    private Integer capacity;
    private String contactName;
    private String contactCellphoneNumber;
    private Integer rating;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

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
}