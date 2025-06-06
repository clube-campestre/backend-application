package com.campestre.clube.backend_application.entity.enums;

import com.campestre.clube.backend_application.exceptions.BadRequestException;

public enum ClassCategory {
    NENHUMA("Nenhuma"),
    AMIGO("Amigo"),
    COMPANHEIRO("Companheiro"),
    PESQUISADOR("Pesquisador"),
    PIONEIRO("Pioneiro"),
    EXCURSIONISTA("Excursionista"),
    GUIA("Guia"),
    AGRUPADAS("Agrupadas"),
    DESBRAVADORES_COMPLETO("Desbravadores Completo"),
    LIDER("Líder"),
    LIDER_MASTER("Líder Master"),
    LIDER_MASTER_AVANCADO("Líder Master Avançado");

    private final String formattedValue;

    ClassCategory(String formattedValue) {
        this.formattedValue = formattedValue;
    }

    public String getFormattedValue() {
        return formattedValue;
    }

    public static ClassCategory fromString(String value) {
        ClassCategory classCategory;
        try {
            classCategory = ClassCategory.valueOf(value.toUpperCase().replace(" ", "_"));
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Class category of member data invalid");
        }
        return classCategory;
    }
}
