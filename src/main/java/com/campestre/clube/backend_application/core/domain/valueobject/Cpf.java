package com.campestre.clube.backend_application.core.domain.valueobject;

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
