package com.campestre.clube.backend_application.infrastructure.web.dtos.transport;

import java.math.BigDecimal;

public record TransportResponseDto(
    Long id,
    BigDecimal price,
    Float travelDistance,
    Integer capacity,
    String companyName,
    String companyNumber,
    String driverName,
    String driverNumber,
    Integer rating
){}