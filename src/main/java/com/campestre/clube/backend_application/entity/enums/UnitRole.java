package com.campestre.clube.backend_application.entity.enums;

public enum UnitRole {
    CONSELHEIRO("Conselheiro"),
    CONSELHEIRO_AUXILIAR("Conselheiro Auxiliar"),
    CAPITAO("Capitão"),
    VICE_CAPITAO("Vice-capitão"),
    TESOUREIRO("Tesoureiro"),
    VICE_TESOUREIRO("Vice-tesoureiro"),
    SECRETARIO("Secretário"),
    VICE_SECRETARIO("Vice-secretário"),
    PADIOLEIRO("Padioleiro"),
    CAPELAO("Capelão"),
    ALMOXARIFADO("Almoxarifado");

    private final String formattedValue;

    UnitRole(String formattedValue) {
        this.formattedValue = formattedValue;
    }

    public String getFormattedValue() {
        return formattedValue;
    }

    public static UnitRole fromString(String value) {
        return EnumUtils.fromString(UnitRole.class, value, "Papel da unidade do membro inválido.");
    }
}
