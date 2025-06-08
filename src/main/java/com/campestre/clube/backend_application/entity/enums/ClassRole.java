package com.campestre.clube.backend_application.entity.enums;

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
        return EnumUtils.fromString(ClassRole.class, value, "Papel da classe do membro inválido.");
    }
}
