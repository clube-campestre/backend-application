package com.campestre.clube.backend_application.core.domain;

import com.campestre.clube.backend_application.core.domain.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Statement {
    private Long id;
    private String information;
    private BigDecimal price;
    private LocalDateTime transactionDate;
    private TransactionType transactionType;
    private Tag tag;

    private Statement(
            Long id, String information, BigDecimal price, LocalDateTime transactionDate,
            TransactionType transactionType, Tag tag
    ) {
        this.id = id;
        this.information = information;
        this.price = price;
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
        this.tag = tag;
    }

    public Statement() {}

    public static Statement of(
            Long id, String information, BigDecimal price, LocalDateTime transactionDate,
            TransactionType transactionType, Tag tag
    ) {
        return new Statement(
                id,
                information,
                price,
                transactionDate,
                transactionType,
                tag
        );
    }

    public static Statement of(
            String information, BigDecimal price, LocalDateTime transactionDate, TransactionType transactionType,
            Tag tag
    ) {
        return new Statement(
                null,
                information,
                price,
                transactionDate,
                transactionType,
                tag
        );
    }

    public Long getId() {
        return id;
    }

    public String getInformation() {
        return information;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
