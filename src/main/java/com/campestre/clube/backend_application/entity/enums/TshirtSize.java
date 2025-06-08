package com.campestre.clube.backend_application.entity.enums;

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
        return EnumUtils.fromString(TshirtSize.class, value, "Tamanho da camiseta do membro inválido.");
    }
}
