package com.campestre.clube.backend_application.core.application.statement.valueobject;

import com.campestre.clube.backend_application.core.domain.enums.TransactionType;

import java.time.Instant;

public record Filter(
        Instant startDate,
        Instant endDate,
        Long tagId,
        TransactionType type,
        String description
) {}
