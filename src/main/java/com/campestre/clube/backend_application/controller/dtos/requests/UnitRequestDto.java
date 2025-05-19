package com.campestre.clube.backend_application.controller.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class UnitRequestDto {
    @NotBlank
    private String surname;
    @Positive
    private Integer score;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
