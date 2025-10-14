package com.campestre.clube.backend_application.infrastructure.persistence.jpa.tag;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tags")
public class TagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String surname;
    private String color;
    private BigDecimal goal;
    private Boolean privateGoal = true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
