package com.campestre.clube.backend_application.core.domain.valueobject;

import static com.campestre.clube.backend_application.core.application.utils.ExceptionExtensions.INVALID_CELLPHONE_NUMBER;

public class CellphoneNumber {
    private String number;

    private CellphoneNumber(String number) {
        this.number = number;
    }

    public static CellphoneNumber of(String number) {
        if (number.length() != 11) throw INVALID_CELLPHONE_NUMBER;
        return new CellphoneNumber(number);
    }

    public String getNumber() {
        return number;
    }
}
