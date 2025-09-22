package com.campestre.clube.backend_application.core.domain.valueobject;

public class MemberContact {
    private String name;
    private CellphoneNumber cellphoneNumber;
    private Email email;

    private MemberContact(String name, CellphoneNumber cellphoneNumber, Email email) {
        this.name = name;
        this.cellphoneNumber = cellphoneNumber;
        this.email = email;
    }

    public static MemberContact of(String name, String number, Email email) {
        return new MemberContact(
                name,
                CellphoneNumber.of(number),
                email
        );
    }

    public String getName() {
        return name;
    }

    public CellphoneNumber getCellphoneNumber() {
        return cellphoneNumber;
    }

    public Email getEmail() {
        return email;
    }
}
