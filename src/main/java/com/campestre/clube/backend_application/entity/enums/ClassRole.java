package com.campestre.clube.backend_application.entity.enums;

import com.campestre.clube.backend_application.exceptions.BadRequestException;

public enum ClassRole {
    INSTRUTOR,
    INSTRUTOR_AUXILIAR,
    MEMBRO;

    public static ClassRole fromString(String value) {
        ClassRole classRole;
        try {
            classRole = ClassRole.valueOf(value.toUpperCase().replace(" ", "_"));
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Class role of member data invalid");
        }
        return classRole;
    }
}
