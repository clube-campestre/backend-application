package com.campestre.clube.backend_application.entity.enums;

import com.campestre.clube.backend_application.exceptions.BadRequestException;

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
        AccessTypeEnum type;
        try {
            type = AccessTypeEnum.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Access type of account invalid");
        }
        return type;
    }
}
