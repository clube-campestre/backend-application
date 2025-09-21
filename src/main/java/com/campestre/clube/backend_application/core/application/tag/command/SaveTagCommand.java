package com.campestre.clube.backend_application.core.application.tag.command;

import java.math.BigDecimal;

public record SaveTagCommand(
        String surname,
        String color,
        BigDecimal goal,
        Boolean privateGoal
){}