package com.campestre.clube.backend_application.entity.enums;

import com.campestre.clube.backend_application.exceptions.BadRequestException;

public enum ClassCategory {
    AMIGO,
    COMPANHEIRO,
    PESQUISADOR,
    PIONEIRO,
    EXCURSIONISTA,
    GUIA,
    AGRUPADAS,
    DESBRAVADORES_COMPLETO,
    LIDER,
    LIDER_MASTER,
    LIDER_MASTER_AVANCADO;

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
