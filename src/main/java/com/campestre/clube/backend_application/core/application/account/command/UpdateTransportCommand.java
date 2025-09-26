package com.campestre.clube.backend_application.core.application.account.command;

import java.math.BigDecimal;

public record UpdateTransportCommand(
        Integer id,
        BigDecimal price,
        Float travelDistance,
        Integer capacity,
        String companyName,
        String companyNumber,
        String driverName,
        String driverNumber,
        Integer rating
){}