package com.campestre.clube.backend_application.controller.dtos.requests;

import jakarta.validation.constraints.NotBlank;

public class GetUnitRequestDto {
    @NotBlank
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
