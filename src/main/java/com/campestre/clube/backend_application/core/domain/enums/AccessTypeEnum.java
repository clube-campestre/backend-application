package com.campestre.clube.backend_application.core.domain.enums;


import static com.campestre.clube.backend_application.core.exceptions.ExceptionExtensions.ERROR_ACCESS_TYPE_ENUM;

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
        return EnumUtils.fromString(AccessTypeEnum.class, value, ERROR_ACCESS_TYPE_ENUM);
    }
}
