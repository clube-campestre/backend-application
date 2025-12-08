package com.campestre.clube.backend_application.core.application.statement.command;

import com.campestre.clube.backend_application.core.domain.enums.TransactionType;

import java.math.BigDecimal;
import java.time.Instant;

public record UpdateStatementCommand(
        Long id,
        String information,
        BigDecimal price,
        Instant transactionDate,
        TransactionType transactionType,
        String tagSurname
){}