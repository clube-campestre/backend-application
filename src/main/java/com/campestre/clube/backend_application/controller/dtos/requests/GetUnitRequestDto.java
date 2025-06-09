package com.campestre.clube.backend_application.controller.dtos.requests;

import jakarta.validation.constraints.Positive;

public class GetUnitRequestDto {
    @Positive
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
