package com.campestre.clube.backend_application.core.domain.enums;

import com.campestre.clube.backend_application.core.application.exceptions.BadRequestException;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

public class EnumUtils {
    private static final Pattern NON_ASCII = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");

    public static <E extends Enum<E>> E fromString(Class<E> enumClass, String value, String errorMessage) {
        if (value == null) throw new BadRequestException(errorMessage);
        try {
            return Enum.valueOf(enumClass, normalize(value).toUpperCase(Locale.ROOT).replace(" ", "_"));
        } catch (IllegalArgumentException e) {
            throw new BadRequestException(errorMessage);
        }
    }

    private static String normalize(String input) {
        return NON_ASCII.matcher(Normalizer.normalize(input, Normalizer.Form.NFD)).replaceAll("");
    }
}
