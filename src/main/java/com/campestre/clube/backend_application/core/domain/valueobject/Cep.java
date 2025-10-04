package com.campestre.clube.backend_application.core.domain.valueobject;

import static com.campestre.clube.backend_application.core.exceptions.ExceptionExtensions.INVALID_CEP;

public class Cep {
    private String number;

    private Cep(String number) {
        this.number = number;
    }

    public static Cep of(String number) {
        if (number.length() != 8) throw INVALID_CEP;
        return new Cep(number);
    }

    public String getNumber() {
        return number;
    }
}
