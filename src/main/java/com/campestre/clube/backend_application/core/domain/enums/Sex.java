package com.campestre.clube.backend_application.core.domain.enums;

import static com.campestre.clube.backend_application.core.exceptions.ExceptionExtensions.ERROR_SEX_ENUM;

public enum Sex {
    OUTRO,
    FEMININO,
    MASCULINO;

    public static Sex fromString(String value) {
        return EnumUtils.fromString(Sex.class, value, ERROR_SEX_ENUM);
    }
}
