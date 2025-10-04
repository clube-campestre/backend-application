package com.campestre.clube.backend_application.core.domain.enums;

import static com.campestre.clube.backend_application.core.exceptions.ExceptionExtensions.ERROR_CLASS_ROLE_ENUM;

public enum ClassRole {
    NENHUMA("Nenhuma"),
    INSTRUTOR("Instrutor"),
    INSTRUTOR_AUXILIAR("Instrutor Auxiliar"),
    MEMBRO("Membro");

    private final String formattedValue;

    ClassRole(String formattedValue) {
        this.formattedValue = formattedValue;
    }

    public String getFormattedValue() {
        return formattedValue;
    }

    public static ClassRole fromString(String value) {
        return EnumUtils.fromString(ClassRole.class, value, ERROR_CLASS_ROLE_ENUM);
    }
}
