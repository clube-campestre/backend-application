package com.campestre.clube.backend_application.core.domain.enums;

import static com.campestre.clube.backend_application.core.exceptions.ExceptionExtensions.ERROR_UNIT_ENUM;

public enum UnitEnum {
    NENHUMA("Nenhuma", 1L, false),
    PANDA("Panda", 2L, true),
    FALCAO("Falcão", 3L, true),
    LINCE("Lince", 4L, true),
    LEAO("Leão", 5L, true),
    AGUIA_REAL("Águia Real", 6L, true),
    TIGRE("Tigre", 7L, true),
    RAPOSA("Raposa", 8L, true),
    URSO("Urso", 9L, true),
    PANTERA("Pantera", 10L, true),
    LOBO("Lobo", 11L, true);

    private final String formattedValue;
    private final Long id;
    private final Boolean hasRanking;

    UnitEnum(String formattedValue, Long id, Boolean hasRanking) {
        this.formattedValue = formattedValue;
        this.id = id;
        this.hasRanking = hasRanking;
    }

    public String getFormattedValue() {
        return formattedValue;
    }

    public Long getId() {
        return id;
    }

    public Boolean getHasRanking() {
        return hasRanking;
    }

    public static UnitEnum fromString(String value) {
        return EnumUtils.fromString(UnitEnum.class, value, ERROR_UNIT_ENUM);
    }
}
