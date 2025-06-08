package com.campestre.clube.backend_application.entity.enums;

import com.campestre.clube.backend_application.exceptions.BadRequestException;

public class EnumUtils {
    public static <E extends Enum<E>> E fromString(Class<E> enumClass, String value, String errorMessage) {
        if (value == null) {
            throw new BadRequestException(errorMessage);
        }
        try {
            return Enum.valueOf(enumClass, value.toUpperCase().replace(" ", "_"));
        } catch (IllegalArgumentException e) {
            throw new BadRequestException(errorMessage);
        }
    }
}
