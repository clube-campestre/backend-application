package com.campestre.clube.backend_application.core.domain.valueobject;

import static com.campestre.clube.backend_application.core.exceptions.ExceptionExtensions.INVALID_CELLPHONE_NUMBER;

public class CellphoneNumber {
    private String number;

    private CellphoneNumber(String number) {
        this.number = number;
    }

    public CellphoneNumber() {}

    public static CellphoneNumber of(String number) {
        if (number.length() == 11 || number.isBlank()) return new CellphoneNumber(number);
        throw INVALID_CELLPHONE_NUMBER;
    }

    public String getNumber() {
        return number;
    }
}
