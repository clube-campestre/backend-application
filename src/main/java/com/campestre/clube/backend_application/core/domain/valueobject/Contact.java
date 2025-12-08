package com.campestre.clube.backend_application.core.domain.valueobject;

public class Contact {
    private String name;
    private CellphoneNumber cellphoneNumber;

    private Contact(String name, CellphoneNumber cellphoneNumber) {
        this.name = name;
        this.cellphoneNumber = cellphoneNumber;
    }

    public Contact() {}

    public static Contact of(String name, String number) {
        return new Contact(
                name,
                CellphoneNumber.of(number)
        );
    }

    public String getName() {
        return name;
    }

    public CellphoneNumber getCellphoneNumber() {
        return cellphoneNumber;
    }
}
