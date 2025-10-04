package com.campestre.clube.backend_application.core.domain.enums;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

public class EnumUtils {
    private static final Pattern NON_ASCII = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");

    public static <E extends Enum<E>> E fromString(Class<E> enumClass, String value, RuntimeException exception) {
        if (value == null) throw exception;
        try {
            return Enum.valueOf(enumClass, normalize(value).toUpperCase(Locale.ROOT).replace(" ", "_"));
        } catch (IllegalArgumentException e) {
            throw exception;
        }
    }

    private static String normalize(String input) {
        return NON_ASCII.matcher(Normalizer.normalize(input, Normalizer.Form.NFD)).replaceAll("");
    }
}
