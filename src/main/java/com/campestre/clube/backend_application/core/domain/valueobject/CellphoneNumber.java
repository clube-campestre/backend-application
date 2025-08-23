package com.campestre.clube.backend_application.core.domain.valueobject;

import com.campestre.clube.backend_application.core.domain.exception.InvalidRequestException;

public class CellphoneNumber {
    private String number;

    private CellphoneNumber(String number) {
        this.number = number;
    }

    public static CellphoneNumber of(String number) {
        if (number.length() != 11) throw new InvalidRequestException("O número de telefone é inválido");
        return new CellphoneNumber(number);
    }

    public String getNumber() {
        return number;
    }
}
