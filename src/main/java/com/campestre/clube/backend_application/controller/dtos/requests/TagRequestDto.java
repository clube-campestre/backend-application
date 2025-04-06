package com.campestre.clube.backend_application.controller.dto.requests;

import com.campestre.clube.backend_application.entity.Tag;
import jakarta.validation.constraints.NotNull;

public class TagRequestDto {
    @NotNull
    private String surname;

    @NotNull
    private String color;


    public TagRequestDto() {
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
}
