package com.campestre.clube.backend_application.core.domain.enums;

import static com.campestre.clube.backend_application.core.exceptions.ExceptionExtensions.ERROR_UNIT_ENUM;

public enum UnitEnum {
    NENHUMA("Nenhuma", 1L),
    PANDA("Panda", 2L),
    FALCAO("Falcão", 3L),
    LINCE("Lince", 4L),
    LEAO("Leão", 5L),
    AGUIA_REAL("Águia Real", 6L),
    TIGRE("Tigre", 7L),
    RAPOSA("Raposa", 8L),
    URSO("Urso", 9L),
    PANTERA("Pantera", 10L),
    LOBO("Lobo", 11L);

    private final String formattedValue;
    private final Long id;

    UnitEnum(String formattedValue, Long id) {
        this.formattedValue = formattedValue;
        this.id = id;
    }

    public String getFormattedValue() {
        return formattedValue;
    }

    public Long getId() {
        return id;
    }

    public static UnitEnum fromString(String value) {
        return EnumUtils.fromString(UnitEnum.class, value, ERROR_UNIT_ENUM);
    }
}
