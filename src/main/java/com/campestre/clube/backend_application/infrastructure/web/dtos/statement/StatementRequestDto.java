package com.campestre.clube.backend_application.infrastructure.web.dtos.statement;


import com.campestre.clube.backend_application.core.domain.enums.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class StatementRequestDto {
    @NotBlank
    private String information;
    @NotNull
    private BigDecimal price;
    @NotNull
    private String transactionDate;
    @NotNull
    private TransactionType transactionType;
    @NotBlank
    private String tagSurname;

    public StatementRequestDto() {}

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getTagSurname() {
        return tagSurname;
    }

    public void setTagSurname(String tagSurname) {
        this.tagSurname = tagSurname;
    }
}
