package com.campestre.clube.backend_application.infrastructure.web.dtos.tag;

import java.math.BigDecimal;

public class TagResponseDto {
    private Long id;
    private String surname;
    private String color;
    private BigDecimal goal;
    private Boolean privateGoal;

    public TagResponseDto(Long id, String surname, String color, BigDecimal goal, Boolean privateGoal) {
        this.id = id;
        this.surname = surname;
        this.color = color;
        this.goal = goal;
        this.privateGoal = privateGoal;
    }

    public TagResponseDto() {
    }

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
