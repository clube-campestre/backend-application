package com.campestre.clube.backend_application.controller.dto.responses;

import com.campestre.clube.backend_application.entity.Statement;
import com.campestre.clube.backend_application.entity.Tag;
import com.campestre.clube.backend_application.entity.enums.TransactionType;

import java.time.LocalDateTime;

public class StatementResponseDto {
    private Integer id;
    private String information;
    private Double price;
    private LocalDateTime transactionDate;
    private TransactionType transactionType;
    private Tag Tag;

    public static StatementResponseDto toResponse(Statement statement){
        StatementResponseDto response = new StatementResponseDto();
        response.setId(statement.getId());
        response.setInformation(statement.getInformation());
        response.setPrice(statement.getPrice());
        response.setTransactionDate(statement.getTransactionDate());
        response.setTransactionType(statement.getTransactionType());
        response.setTag(statement.getTag());

        return response;
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
        return Tag;
    }

    public void setTag(Tag tag) {
        this.Tag = tag;
    }
}
