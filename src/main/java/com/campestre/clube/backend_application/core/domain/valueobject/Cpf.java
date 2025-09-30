package com.campestre.clube.backend_application.core.domain.valueobject;

import com.campestre.clube.backend_application.core.application.exceptions.InvalidRequestException;

public class Cpf {
    private String number;

    private Cpf(String number) {
        this.number = number;
    }

    public static Cpf of(String number) {
        if (number.length() != 11) throw new InvalidRequestException("O número do CPF é inválido");
        return new Cpf(number);
    }

    public String getNumber() {
        return number;
    }
}
