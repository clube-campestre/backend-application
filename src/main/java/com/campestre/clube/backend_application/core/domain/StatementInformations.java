package com.campestre.clube.backend_application.core.domain;

import com.campestre.clube.backend_application.core.application.valueobject.Pagination;

import java.math.BigDecimal;
import java.util.List;

public class StatementInformations {
    private List<Statement> statements;
    private Pagination pagination;
    private BigDecimal totalPrice;

    private StatementInformations(List<Statement> statements, Pagination pagination, BigDecimal totalPrice) {
        this.statements = statements;
        this.pagination = pagination;
        this.totalPrice = totalPrice;
    }

    public StatementInformations() {}

    public static StatementInformations of(List<Statement> statements, Pagination pagination, BigDecimal totalPrice) {
        return new StatementInformations(statements, pagination, totalPrice);
    }

    public List<Statement> getStatements() {
        return statements;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
}
