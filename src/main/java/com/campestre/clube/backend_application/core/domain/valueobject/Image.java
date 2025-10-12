package com.campestre.clube.backend_application.core.domain.valueobject;

import static com.campestre.clube.backend_application.core.exceptions.ExceptionExtensions.*;

public class Image {
    private byte[] value;
    private String format;

    private Image(byte[] value, String format) {
        this.value = value;
        this.format = format;
    }

    public static Image of(byte[] value, String format) {
        if (format.contains("image/")) throw INVALID_IMAGE_FORMAT;
        return new Image(value, format);
    }

    public byte[] getValue() {
        return value;
    }

    public String getFormat() {
        return format;
    }
}
