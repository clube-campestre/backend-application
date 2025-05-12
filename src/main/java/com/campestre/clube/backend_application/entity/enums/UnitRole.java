package com.campestre.clube.backend_application.entity.enums;

import com.campestre.clube.backend_application.exceptions.BadRequestException;

public enum UnitRole {
    CONSELHEIRO,
    CONSELHEIRO_AUXILIAR,
    CAPITAO,
    VICE_CAPITAO,
    TESOUREIRO,
    VICE_TESOUREIRO,
    SECRETARIO,
    VICE_SECRETARIO,
    PADIOLEIRO,
    CAPELAO,
    ALMO_XARIFADO;

    public static UnitRole fromString(String value) {
        UnitRole unitRole;
        try {
            unitRole = UnitRole.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Unit role of member data invalid");
        }
        return unitRole;
    }
}
