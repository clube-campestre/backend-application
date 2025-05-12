package com.campestre.clube.backend_application.entity.enums;

import com.campestre.clube.backend_application.exceptions.BadRequestException;

public enum Sex {
    OUTRO,
    FEMININO,
    MASCULINO;

    public static Sex fromString(String value) {
        Sex sex;
        try {
            sex = Sex.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Sex of member data invalid");
        }
        return sex;
    }
}
