package com.campestre.clube.backend_application.mapper;

import com.campestre.clube.backend_application.controller.dtos.requests.StatementRequestDto;
import com.campestre.clube.backend_application.entity.Statement;
import jakarta.validation.Valid;

public class StatementMapper {
    public static Statement toEntity(@Valid StatementRequestDto dto){
        Statement statement = new Statement();
        statement.setInformation(dto.getInformation());
        statement.setPrice(dto.getPrice());
        statement.setTransactionDate(dto.getTransactionDate());
        statement.setTransactionType(dto.getTransactionType());
        return statement;
    }
}
