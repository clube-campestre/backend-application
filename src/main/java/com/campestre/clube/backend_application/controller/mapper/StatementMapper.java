package com.campestre.clube.backend_application.controller.mapper;

import com.campestre.clube.backend_application.controller.dtos.requests.StatementRequestDto;
import com.campestre.clube.backend_application.controller.dtos.responses.StatementResponseDto;
import com.campestre.clube.backend_application.entity.Statement;

import java.util.List;

public abstract class StatementMapper {
    public static Statement toEntity(StatementRequestDto dto){
        Statement statement = new Statement();
        statement.setInformation(dto.getInformation());
        statement.setPrice(dto.getPrice());
        statement.setTransactionDate(dto.getTransactionDate());
        statement.setTransactionType(dto.getTransactionType());
        return statement;
    }

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

    public static List<StatementResponseDto> toResponse(List<Statement> statements){
        return statements.stream().map(StatementMapper::toResponse).toList();
    }
}
