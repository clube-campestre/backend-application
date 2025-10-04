package com.campestre.clube.backend_application.core.domain.enums;

import static com.campestre.clube.backend_application.core.exceptions.ExceptionExtensions.ERROR_TSHIRT_SIZE_ENUM;

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
        return EnumUtils.fromString(TshirtSize.class, value, ERROR_TSHIRT_SIZE_ENUM);
    }
}
