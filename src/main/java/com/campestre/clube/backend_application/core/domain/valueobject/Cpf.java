package com.campestre.clube.backend_application.core.domain.valueobject;

import static com.campestre.clube.backend_application.core.application.utils.ExceptionExtensions.INVALID_CPF;

public class Cpf {
    private String number;

    private Cpf(String number) {
        this.number = number;
    }

    public static Cpf of(String number) {
//        if (number.length() != 11) throw INVALID_CPF;
        return new Cpf(number);
    }

    public String getNumber() {
        return number;
    }
}
