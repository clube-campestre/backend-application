package com.campestre.clube.backend_application.infrastructure.web.dtos.tag;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public class UpdateTagRequestDto {
    @NotBlank
    private String surname;
    @NotBlank
    private String color;
    private BigDecimal goal;
    private Boolean privateGoal = false;

    public UpdateTagRequestDto(String surname, String color, BigDecimal goal, Boolean privateGoal) {
        this.surname = surname;
        this.color = color;
        this.goal = goal;
        this.privateGoal = privateGoal;
    }

    public UpdateTagRequestDto() {
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
