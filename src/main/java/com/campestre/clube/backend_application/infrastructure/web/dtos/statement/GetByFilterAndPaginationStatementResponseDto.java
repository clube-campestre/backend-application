package com.campestre.clube.backend_application.infrastructure.web.dtos.statement;

import java.math.BigDecimal;
import java.util.List;

public record GetByFilterAndPaginationStatementResponseDto(
    Integer pageNumber,
    Integer pageSize,
    Long totalItems,
    Integer totalPages,
    BigDecimal totalPrice,
    List<StatementResponseDto> items
) {}