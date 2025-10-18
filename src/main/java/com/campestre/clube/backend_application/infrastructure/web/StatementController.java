package com.campestre.clube.backend_application.infrastructure.web;

import com.campestre.clube.backend_application.core.application.goal.GetGoalByTagIdUseCase;
import com.campestre.clube.backend_application.core.application.goal.command.GetGoalByTagIdCommand;
import com.campestre.clube.backend_application.core.application.statement.*;
import com.campestre.clube.backend_application.core.application.statement.command.DeleteStatementByIdCommand;
import com.campestre.clube.backend_application.core.application.statement.command.DeleteStatementByTagSurnameCommand;
import com.campestre.clube.backend_application.core.application.statement.command.GetStatementByIdCommand;
import com.campestre.clube.backend_application.core.application.statement.command.UpdateStatementCommand;
import com.campestre.clube.backend_application.core.application.statement.valueobject.Filter;
import com.campestre.clube.backend_application.core.application.valueobject.Pagination;
import com.campestre.clube.backend_application.core.domain.StatementInformations;
import com.campestre.clube.backend_application.core.domain.enums.TransactionType;
import com.campestre.clube.backend_application.infrastructure.web.dtos.goal.GoalResponseDto;
import com.campestre.clube.backend_application.infrastructure.web.dtos.statement.GetByFilterAndPaginationStatementResponseDto;
import com.campestre.clube.backend_application.infrastructure.web.dtos.statement.StatementRequestDto;
import com.campestre.clube.backend_application.infrastructure.web.dtos.statement.StatementResponseDto;
import com.campestre.clube.backend_application.infrastructure.web.mappers.StatementDtoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/statements")
@CrossOrigin("*")
@Tag(name = "Statement Controller", description = "Statement data routes")
public class StatementController {
    private final SaveStatementUseCase saveStatementUseCase;
    private final UpdateStatementUseCase updateStatementUseCase;
    private final DeleteStatementByIdUseCase deleteStatementByIdUseCase;
    private final DeleteStatementByTagSurnameUseCase deleteStatementByTagSurnameUseCase;
    private final GetStatementByIdUseCase getStatementByIdUseCase;
    private final GetGoalByTagIdUseCase getGoalByTagIdUseCase;
    private final ListStatementByFilterAndPaginationUseCase listStatementByFilterAndPaginationUseCase;

    public StatementController(
            SaveStatementUseCase saveStatementUseCase,
            UpdateStatementUseCase updateStatementUseCase,
            DeleteStatementByIdUseCase deleteStatementByIdUseCase,
            DeleteStatementByTagSurnameUseCase deleteStatementByTagSurnameUseCase,
            GetStatementByIdUseCase getStatementByIdUseCase,
            GetGoalByTagIdUseCase getGoalByTagIdUseCase,
            ListStatementByFilterAndPaginationUseCase listStatementByFilterAndPaginationUseCase
    ) {
        this.saveStatementUseCase = saveStatementUseCase;
        this.updateStatementUseCase = updateStatementUseCase;
        this.deleteStatementByIdUseCase = deleteStatementByIdUseCase;
        this.deleteStatementByTagSurnameUseCase = deleteStatementByTagSurnameUseCase;
        this.getStatementByIdUseCase = getStatementByIdUseCase;
        this.getGoalByTagIdUseCase = getGoalByTagIdUseCase;
        this.listStatementByFilterAndPaginationUseCase = listStatementByFilterAndPaginationUseCase;
    }

    @PostMapping
    @Operation(summary = "Endpoint for create statement")
    @PreAuthorize("hasAnyRole('DIRETOR', 'EXECUTIVO', 'TESOURARIA', 'SUPERVISOR')")
    public ResponseEntity<StatementResponseDto> register(@RequestBody @Valid StatementRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(StatementDtoMapper.toResponse(
                saveStatementUseCase.execute(StatementDtoMapper.toCommand(dto))
        ));
    }

    @GetMapping
    @Operation(summary = "Endpoint for get statement by filter and pagination")
    @PreAuthorize("hasAnyRole('DIRETOR', 'EXECUTIVO', 'TESOURARIA', 'SUPERVISOR')")
    public ResponseEntity<GetByFilterAndPaginationStatementResponseDto> getByFilterAndPagination(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @RequestParam(required = false) Long tagId,
            @RequestParam(required = false) TransactionType type,
            @RequestParam(required = false) String description,

            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        StatementInformations statementInformations = listStatementByFilterAndPaginationUseCase.execute(
                StatementDtoMapper.toCommand(
                        new Filter(startDate, endDate, tagId, type, description), Pagination.of(page, size)
                )
        );
        return ResponseEntity.ok(StatementDtoMapper.toResponse(statementInformations));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Endpoint for get statement by id")
    @PreAuthorize("hasAnyRole('DIRETOR', 'EXECUTIVO', 'TESOURARIA', 'SUPERVISOR')")
    public ResponseEntity<StatementResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(StatementDtoMapper.toResponse(
                getStatementByIdUseCase.execute(new GetStatementByIdCommand(id))
        ));
    }

    @GetMapping("/goal")
    @Operation(summary = "Endpoint for get goal by tag id")
    @PreAuthorize("hasAnyRole('DIRETOR', 'EXECUTIVO', 'TESOURARIA', 'SUPERVISOR')")
    public ResponseEntity<GoalResponseDto> getGoalByTagId(@RequestParam Long tagId) {
        return ResponseEntity.ok(StatementDtoMapper.toResponse(
                getGoalByTagIdUseCase.execute(new GetGoalByTagIdCommand(tagId))
        ));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Endpoint for update statement by id")
    @PreAuthorize("hasAnyRole('DIRETOR', 'EXECUTIVO', 'TESOURARIA', 'SUPERVISOR')")
    public ResponseEntity<StatementResponseDto> update(@RequestBody @Valid UpdateStatementCommand dto) {
        return ResponseEntity.ok(StatementDtoMapper.toResponse(
                updateStatementUseCase.execute(dto)
        ));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Endpoint for remove statement by id")
    @PreAuthorize("hasAnyRole('DIRETOR', 'EXECUTIVO', 'TESOURARIA', 'SUPERVISOR')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteStatementByIdUseCase.execute(new DeleteStatementByIdCommand(id));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/tag")
    @Operation(summary = "Endpoint for remove statement by tag")
    @PreAuthorize("hasAnyRole('DIRETOR', 'EXECUTIVO', 'TESOURARIA', 'SUPERVISOR')")
    public ResponseEntity<Void> deleteByTag(@RequestParam String tagName) {
        deleteStatementByTagSurnameUseCase.execute(new DeleteStatementByTagSurnameCommand(tagName));
        return ResponseEntity.ok().build();
    }
}
