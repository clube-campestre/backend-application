package com.campestre.clube.backend_application.core.domain.valueobject;

import com.campestre.clube.backend_application.core.domain.exception.InvalidRequestException;

public class Cns {
    private String number;

    private Cns(String number) {
        this.number = number;
    }

    public static Cns of(String number) {
        if (number.length() != 15) throw new InvalidRequestException("O número do CNS é inválido");
        return new Cns(number);
    }

    public String getNumber() {
        return number;
    }
}
