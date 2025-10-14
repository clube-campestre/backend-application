package com.campestre.clube.backend_application.infrastructure.web.dtos.statement;

import com.campestre.clube.backend_application.core.domain.enums.TransactionType;
import com.campestre.clube.backend_application.infrastructure.web.dtos.tag.TagResponseDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record StatementResponseDto(
    Long id,
    String information,
    BigDecimal price,
    LocalDateTime transactionDate,
    TransactionType transactionType,
    TagResponseDto tag
){}