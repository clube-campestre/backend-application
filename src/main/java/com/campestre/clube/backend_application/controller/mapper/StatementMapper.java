package com.campestre.clube.backend_application.controller.mapper;

import com.campestre.clube.backend_application.controller.dtos.requests.StatementRequestDto;
import com.campestre.clube.backend_application.controller.dtos.responses.GetByFilterAndPaginationStatementResponseDto;
import com.campestre.clube.backend_application.controller.dtos.responses.GoalResponseDto;
import com.campestre.clube.backend_application.controller.dtos.responses.StatementResponseDto;
import com.campestre.clube.backend_application.entity.Statement;
import com.campestre.clube.backend_application.entity.Tag;
import org.antlr.v4.runtime.misc.Pair;

import java.util.List;

public abstract class StatementMapper {
    public static Statement toEntity(StatementRequestDto dto){
        return new Statement(dto.getInformation(), dto.getPrice(), dto.getTransactionDate(), dto.getTransactionType());
    }

    public static StatementResponseDto toResponse(Statement statement){
        return new StatementResponseDto(
                statement.getId(), statement.getInformation(), statement.getPrice(), statement.getTransactionDate(),
                statement.getTransactionType(), statement.getTag()
        );
    }

    public static List<StatementResponseDto> toResponse(List<Statement> statements){
        return statements.stream().map(StatementMapper::toResponse).toList();
    }

    public static GetByFilterAndPaginationStatementResponseDto toResponse(
            List<Statement> statements,
            Integer pageNumber,
            Integer pageSize,
            Long totalItems,
            Integer totalPages,
            Double totalPrice
    ){
        return new GetByFilterAndPaginationStatementResponseDto(
                pageNumber, pageSize, totalItems, totalPages, totalPrice, StatementMapper.toResponse(statements)
        );
    }

    public static GoalResponseDto toResponse(Pair<Double, Tag> result){
        return new GoalResponseDto(result.a, result.b);
    }
}
