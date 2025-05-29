package com.campestre.clube.backend_application.entity.enums;

import com.campestre.clube.backend_application.exceptions.BadRequestException;

public enum UnitEnum {
    PANDA,
    FALCAO,
    LINCE,
    LEAO,
    AGUIA_REAL,
    TIGRE,
    RAPOSA,
    URSO,
    PANTERA,
    LOBO;

    public static UnitEnum fromString(String value) {
        UnitEnum unit;
        try {
            unit = UnitEnum.valueOf(value.toUpperCase().replace(" ", "_"));
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Unit of member data invalid");
        }
        return unit;
    }
}
