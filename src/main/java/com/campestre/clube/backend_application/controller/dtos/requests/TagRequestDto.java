package com.campestre.clube.backend_application.controller.dtos.requests;

import jakarta.validation.constraints.NotBlank;

public class TagRequestDto {
    @NotBlank
    private String surname;
    @NotBlank
    private String color;

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
}
