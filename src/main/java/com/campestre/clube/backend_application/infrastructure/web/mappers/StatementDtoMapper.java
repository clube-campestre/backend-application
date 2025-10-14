package com.campestre.clube.backend_application.infrastructure.web.mappers;

import com.campestre.clube.backend_application.core.application.statement.command.ListStatementByFilterAndPaginationCommand;
import com.campestre.clube.backend_application.core.application.statement.command.SaveStatementCommand;
import com.campestre.clube.backend_application.core.application.statement.command.UpdateStatementCommand;
import com.campestre.clube.backend_application.core.application.statement.valueobject.Filter;
import com.campestre.clube.backend_application.core.application.valueobject.Pagination;
import com.campestre.clube.backend_application.core.domain.Goal;
import com.campestre.clube.backend_application.core.domain.Statement;
import com.campestre.clube.backend_application.core.domain.StatementInformations;
import com.campestre.clube.backend_application.infrastructure.persistence.jpa.tag.TagEntityMapper;
import com.campestre.clube.backend_application.infrastructure.web.dtos.goal.GoalResponseDto;
import com.campestre.clube.backend_application.infrastructure.web.dtos.statement.GetByFilterAndPaginationStatementResponseDto;
import com.campestre.clube.backend_application.infrastructure.web.dtos.statement.StatementRequestDto;
import com.campestre.clube.backend_application.infrastructure.web.dtos.statement.StatementResponseDto;

import java.util.stream.Collectors;

public class StatementDtoMapper {

    public static SaveStatementCommand toCommand(StatementRequestDto dto) {
        return new SaveStatementCommand(
                dto.getInformation(),
                dto.getPrice(),
                dto.getTransactionDate(),
                dto.getTransactionType(),
                dto.getTagSurname()
        );
    }

    public static UpdateStatementCommand toCommand(StatementRequestDto dto, Long id) {
        return new UpdateStatementCommand(
                id,
                dto.getInformation(),
                dto.getPrice(),
                dto.getTransactionDate(),
                dto.getTransactionType(),
                dto.getTagSurname()
        );
    }

    public static ListStatementByFilterAndPaginationCommand toCommand(
            Filter filter, Pagination pagination
    ) {
        return new ListStatementByFilterAndPaginationCommand(filter, pagination);
    }

    public static StatementResponseDto toResponse(Statement statement) {
        return new StatementResponseDto(
                statement.getId(),
                statement.getInformation(),
                statement.getPrice(),
                statement.getTransactionDate(),
                statement.getTransactionType(),
                TagEntityMapper.toResponse(statement.getTag())
        );
    }

    public static GoalResponseDto toResponse(Goal goal) {
        return new GoalResponseDto(goal.getTotalPrice(), goal.getTag());
    }

    public static GetByFilterAndPaginationStatementResponseDto toResponse(StatementInformations statementInformations) {
        return new GetByFilterAndPaginationStatementResponseDto(
                statementInformations.pagination().getPageNumber(),
                statementInformations.pagination().getPageSize(),
                statementInformations.pagination().getTotalItems(),
                statementInformations.pagination().getTotalPages(),
                statementInformations.totalPrice(),
                statementInformations.statements()
                        .stream().map(StatementDtoMapper::toResponse).collect(Collectors.toList())
        );
    }
}