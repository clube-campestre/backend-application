package com.campestre.clube.backend_application.entity.enums;

import com.campestre.clube.backend_application.exceptions.BadRequestException;

public enum AccessTypeEnum {
    DIRETOR,
    EXECUTIVO,
    TESOURARIA,
    SUPERVISOR;

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
