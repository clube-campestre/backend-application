package com.campestre.clube.backend_application.core.application.statement.command;

import com.campestre.clube.backend_application.core.domain.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record UpdateStatementCommand(
        Integer id,
        String information,
        BigDecimal price,
        LocalDateTime transactionDate,
        TransactionType transactionType,
        String tagSurname
){}