package com.campestre.clube.backend_application.core.domain.enums;

import static com.campestre.clube.backend_application.core.exceptions.ExceptionExtensions.ERROR_UNIT_ENUM;

public enum UnitEnum {
    PANDA("Panda", 1),
    FALCAO("Falcão", 2),
    LINCE("Lince", 3),
    LEAO("Leão", 4),
    AGUIA_REAL("Águia Real", 5),
    TIGRE("Tigre", 6),
    RAPOSA("Raposa", 7),
    URSO("Urso", 8),
    PANTERA("Pantera", 9),
    LOBO("Lobo", 10);

    private final String formattedValue;
    private final Integer id;

    UnitEnum(String formattedValue, Integer id) {
        this.formattedValue = formattedValue;
        this.id = id;
    }

    public String getFormattedValue() {
        return formattedValue;
    }

    public Integer getId() {
        return id;
    }

    public static UnitEnum fromString(String value) {
        return EnumUtils.fromString(UnitEnum.class, value, ERROR_UNIT_ENUM);
    }
}
