package com.campestre.clube.backend_application.controller.dtos.responses;

import com.campestre.clube.backend_application.entity.Tag;
import com.campestre.clube.backend_application.entity.enums.TransactionType;

import java.time.LocalDateTime;

public class StatementResponseDto {
    private Integer id;
    private String information;
    private Double price;
    private LocalDateTime transactionDate;
    private TransactionType transactionType;
    private Tag tag;

    public StatementResponseDto(Integer id, String information, Double price, LocalDateTime transactionDate, TransactionType transactionType, Tag tag) {
        this.id = id;
        this.information = information;
        this.price = price;
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
        this.tag = tag;
    }

    public StatementResponseDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
