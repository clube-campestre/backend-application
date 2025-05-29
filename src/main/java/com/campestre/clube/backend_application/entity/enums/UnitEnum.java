package com.campestre.clube.backend_application.entity.enums;

import com.campestre.clube.backend_application.exceptions.BadRequestException;

public enum UnitEnum {
    PANDA,
    FALCÃO,
    LINCE,
    LEÃO,
    AGUIA_REAL,
    TIGRE,
    RAPOSA,
    URSO,
    PANTERA,
    LOBO;

    public static UnitEnum fromString(String value) {
        UnitEnum unit;
        try {
            unit = UnitEnum.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Unit of member data invalid");
        }
        return unit;
    }
}
