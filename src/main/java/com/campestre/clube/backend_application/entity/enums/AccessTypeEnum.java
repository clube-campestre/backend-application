package com.campestre.clube.backend_application.entity.enums;

public enum AccessTypeEnum {
    DIRETOR("Diretor"),
    EXECUTIVO("Executivo"),
    TESOURARIA("Tesouraria"),
    SUPERVISOR("Supervisor");

    private final String formattedValue;

    AccessTypeEnum(String formattedValue) {
        this.formattedValue = formattedValue;
    }

    public String getFormattedValue() {
        return formattedValue;
    }

    public static AccessTypeEnum fromString(String value) {
        return EnumUtils.fromString(AccessTypeEnum.class, value, "Tipo de acesso da conta inválido.");
    }
}
