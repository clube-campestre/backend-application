package com.campestre.clube.backend_application.core.domain.enums;

public enum Sex {
    OUTRO,
    FEMININO,
    MASCULINO;

    public static Sex fromString(String value) {
        return EnumUtils.fromString(Sex.class, value, "Sexo informado inválido.");
    }
}
