package com.campestre.clube.backend_application.mapper;

import com.campestre.clube.backend_application.controller.dto.requests.StatementRequestDto;
import com.campestre.clube.backend_application.entity.Statement;

public class StatementMapper {
    public static Statement toEntity(StatementRequestDto dto){
        Statement statement = new Statement();
        statement.setInformation(dto.getInformation());
        statement.setPrice(dto.getPrice());
        statement.setTransactionDate(dto.getTransactionDate());
        statement.setTransactionType(dto.getTransactionType());
        return statement;
    }
}
