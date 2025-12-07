package com.campestre.clube.backend_application.core.domain.valueobject;

public class Cns {
    private String number;

    private Cns(String number) {
        this.number = number;
    }

    public Cns() {}

    public static Cns of(String number) {
//        if (number.length() != 15) throw INVALID_CNS;
        return new Cns(number);
    }

    public String getNumber() {
        return number;
    }
}
