package com.campestre.clube.backend_application.core.application.unit.command;

public record UpdateUnitScoreCommand(
        String unitName,
        Integer newScore
){}