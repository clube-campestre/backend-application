package com.campestre.clube.backend_application.controller.dtos.responses;

public class UnitEnumResponseDto {
    private String unitName;
    private String formattedValue;

    public UnitEnumResponseDto(String unitName, String formattedValue) {
        this.unitName = unitName;
        this.formattedValue = formattedValue;
    }

    public UnitEnumResponseDto() {
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getFormattedValue() {
        return formattedValue;
    }

    public void setFormattedValue(String formattedValue) {
        this.formattedValue = formattedValue;
    }
}
