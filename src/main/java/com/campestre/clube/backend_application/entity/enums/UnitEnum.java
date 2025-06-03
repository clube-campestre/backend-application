package com.campestre.clube.backend_application.entity.enums;

import com.campestre.clube.backend_application.exceptions.BadRequestException;

public enum UnitEnum {
    PANDA("Panda"),
    FALCAO("Falcão"),
    LINCE("Lince"),
    LEAO("Leão"),
    AGUIA_REAL("Águia Real"),
    TIGRE("Tigre"),
    RAPOSA("Raposa"),
    URSO("Urso"),
    PANTERA("Pantera"),
    LOBO("Lobo");

    private String formattedValue;

    UnitEnum(String formattedValue) {
    }

    public String getFormattedValue() {
        return formattedValue;
    }

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
