package com.campestre.clube.backend_application.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String surname;
    private String color;
    private BigDecimal goal;
    private Boolean privateGoal;

    public Tag(String surname, String color, BigDecimal goal, Boolean privateGoal) {
        this.surname = surname;
        this.color = color;
        this.goal = goal;
        this.privateGoal = privateGoal;
    }

    public Tag() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public BigDecimal getGoal() {
        return goal;
    }

    public void setGoal(BigDecimal goal) {
        this.goal = goal;
    }

    public Boolean getPrivateGoal() {
        return privateGoal;
    }

    public void setPrivateGoal(Boolean privateGoal) {
        this.privateGoal = privateGoal;
    }
}
