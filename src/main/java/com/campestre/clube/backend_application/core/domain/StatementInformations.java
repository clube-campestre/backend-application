package com.campestre.clube.backend_application.core.domain;

import com.campestre.clube.backend_application.core.application.valueobject.Pagination;

import java.math.BigDecimal;
import java.util.List;

public record StatementInformations(
    List<Statement> statements,
    Pagination pagination,
    BigDecimal totalPrice
) {}
