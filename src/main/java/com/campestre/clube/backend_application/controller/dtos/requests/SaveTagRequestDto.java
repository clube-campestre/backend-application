package com.campestre.clube.backend_application.controller.dtos.requests;

import jakarta.validation.constraints.NotNull;

public class SaveTagRequestDto {
    @NotNull
    private String surname;
    @NotNull
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
