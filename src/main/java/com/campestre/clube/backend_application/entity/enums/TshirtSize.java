package com.campestre.clube.backend_application.entity.enums;

import com.campestre.clube.backend_application.exceptions.BadRequestException;

public enum TshirtSize {
    PP,
    P,
    M,
    G,
    GG,
    XG,
    XG1,
    XG2;

    public static TshirtSize fromString(String value) {
        TshirtSize tshirtSize;
        try {
            tshirtSize = TshirtSize.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("T-shirt size of member data invalid");
        }
        return tshirtSize;
    }
}
