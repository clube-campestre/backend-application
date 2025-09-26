package com.campestre.clube.backend_application.core.application.unit.command;

public record ChangeUnitScoreCommand(
        String unitName,
        Integer score,
        Boolean isSum
){}