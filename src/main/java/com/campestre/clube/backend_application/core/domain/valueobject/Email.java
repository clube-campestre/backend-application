package com.campestre.clube.backend_application.core.domain.valueobject;

public class Email {
    private String value;

    private Email(String value) {
        this.value = value;
    }

    public static Email of(String value) {
        return new Email(value);
    }

    public String getValue() {
        return value;
    }
}
