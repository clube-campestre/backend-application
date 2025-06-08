package com.campestre.clube.backend_application.controller;

import com.campestre.clube.backend_application.controller.dtos.requests.StatementRequestDto;
import com.campestre.clube.backend_application.controller.dtos.responses.GetByFilterAndPaginationStatementResponseDto;
import com.campestre.clube.backend_application.controller.dtos.responses.GoalResponseDto;
import com.campestre.clube.backend_application.controller.dtos.responses.StatementResponseDto;
import com.campestre.clube.backend_application.controller.mapper.StatementMapper;
import com.campestre.clube.backend_application.entity.models.Pagination;
import com.campestre.clube.backend_application.entity.Statement;
import com.campestre.clube.backend_application.entity.enums.TransactionType;
import com.campestre.clube.backend_application.service.StatementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.antlr.v4.runtime.misc.Triple;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("statements")
@CrossOrigin("*")
@Tag(name = "Statement Controller", description = "Statement data routes")
public class StatementController {

    private final StatementService statementService;

    public StatementController(StatementService statementService) {
        this.statementService = statementService;
    }

    @Operation(summary = "Endpoint for create statement")
    @PostMapping
    public ResponseEntity<StatementResponseDto> register(@RequestBody @Valid StatementRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(StatementMapper.toResponse(
                statementService.register(StatementMapper.toEntity(dto), dto.getTagName())
        ));
    }

    @Operation(summary = "Endpoint for get statement by filter and pagination")
    @GetMapping
    public ResponseEntity<GetByFilterAndPaginationStatementResponseDto> getByFilterAndPagination(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime startDate,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime endDate,

            @RequestParam(required = false) Integer tagId,
            @RequestParam(required = false) TransactionType type,
            @RequestParam(required = false) String description,

            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        Triple<List<Statement>, Pagination, Double> data = statementService
                .getByFilterAndPagination(startDate, endDate, tagId, type, description, page, size);
        return ResponseEntity.ok(StatementMapper.toResponse(
                data.a,
                data.b.getPageNumber(),
                data.b.getPageSize(),
                data.b.getTotalItems(),
                data.b.getTotalPages(),
                data.c
        ));
    }

    @Operation(summary = "Endpoint for get statement by id")
    @GetMapping("/{id}")
    public ResponseEntity<StatementResponseDto> getById(@PathVariable Integer id) {
        var statement = statementService.getById(id);
        return ResponseEntity.ok(StatementMapper.toResponse(statement));
    }

    @Operation(summary = "Endpoint for get goal by tag id")
    @GetMapping("/goal")
    public ResponseEntity<GoalResponseDto> getGoalByTagId(@RequestParam Integer tagId) {
        return ResponseEntity.ok(StatementMapper.toResponse(statementService.getGoalByTagId(tagId)));
    }

    @Operation(summary = "Endpoint for update statement by id")
    @PutMapping("/{id}")
    public ResponseEntity<StatementResponseDto> update(
            @RequestBody @Valid StatementRequestDto dto,
            @PathVariable Integer id) {

        var updated = statementService.update(dto, id);
        return ResponseEntity.ok(StatementMapper.toResponse(updated));
    }

    @Operation(summary = "Endpoint for remove statement by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        statementService.delete(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Endpoint for remove statement by tag")
    @DeleteMapping("/tag")
    public ResponseEntity<Void> deleteByTag(@RequestParam String tagName) {
        statementService.deleteByTag(tagName);
        return ResponseEntity.ok().build();
    }
}
