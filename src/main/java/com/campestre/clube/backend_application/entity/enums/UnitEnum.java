package com.campestre.clube.backend_application.entity.enums;

import com.campestre.clube.backend_application.exceptions.BadRequestException;
import com.campestre.clube.backend_application.exceptions.NotFoundException;

import java.util.Arrays;
import java.util.Objects;

public enum UnitEnum {
    PANDA("Panda", 1),
    FALCAO("Falcão", 2),
    LINCE("Lince", 3),
    LEAO("Leão", 4),
    AGUIA_REAL("Águia Real", 5),
    TIGRE("Tigre", 6),
    RAPOSA("Raposa", 7),
    URSO("Urso", 8),
    PANTERA("Pantera", 9),
    LOBO("Lobo", 10);

    private String formattedValue;
    private Integer id;

    UnitEnum(String formattedValue, Integer id) {
    }

    public String getFormattedValue() {
        return formattedValue;
    }

    public Integer getId() {
        return id;
    }

    public static UnitEnum findByIdOrThrow(Integer unitId) {
        if (unitId == null) throw new BadRequestException("Unit id cannot be null");
        return Arrays.stream(values())
                .filter(unit -> Objects.equals(unit.id, unitId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Unit by id [%s] not found".formatted(unitId)));
    }

    public static UnitEnum fromString(String value) {
        UnitEnum unit;
        try {
            unit = UnitEnum.valueOf(value.toUpperCase().replace(" ", "_"));
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Unit of member data invalid");
        }
        return unit;
    }
}
