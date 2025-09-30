package com.campestre.clube.backend_application.core.domain.valueobject;

import com.campestre.clube.backend_application.core.application.exceptions.InvalidRequestException;

public class Cep {
    private String number;

    private Cep(String number) {
        this.number = number;
    }

    public static Cep of(String number) {
        if (number.length() != 8) throw new InvalidRequestException("O número do CEP é inválido");
        return new Cep(number);
    }

    public String getNumber() {
        return number;
    }
}
