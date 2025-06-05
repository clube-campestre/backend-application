package com.campestre.clube.backend_application.controller.dtos.responses;

public class UnitOrClassEnumResponseDto {
    private String unitOrClass;
    private String formattedValue;

    public UnitOrClassEnumResponseDto(String unitOrClass, String formattedValue) {
        this.unitOrClass = unitOrClass;
        this.formattedValue = formattedValue;
    }

    public UnitOrClassEnumResponseDto() {
    }

    public String getUnitOrClass() {
        return unitOrClass;
    }

    public void setUnitOrClass(String unitOrClass) {
        this.unitOrClass = unitOrClass;
    }

    public String getFormattedValue() {
        return formattedValue;
    }

    public void setFormattedValue(String formattedValue) {
        this.formattedValue = formattedValue;
    }
}
