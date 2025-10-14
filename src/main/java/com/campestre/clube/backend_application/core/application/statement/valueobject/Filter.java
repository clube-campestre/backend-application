package com.campestre.clube.backend_application.core.application.statement.valueobject;

import com.campestre.clube.backend_application.core.domain.enums.TransactionType;

import java.time.LocalDateTime;

public record Filter(
        LocalDateTime startDate,
        LocalDateTime endDate,
        Long tagId,
        TransactionType type,
        String description
) {}
