package com.campestre.clube.backend_application.controller;

import com.campestre.clube.backend_application.controller.dtos.requests.StatementRequestDto;
import com.campestre.clube.backend_application.controller.dtos.responses.StatementResponseDto;
import com.campestre.clube.backend_application.controller.mapper.StatementMapper;
import com.campestre.clube.backend_application.entity.enums.TransactionType;
import com.campestre.clube.backend_application.service.StatementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("statements")
@Tag(name = "Statement")
public class StatementController {

    private final StatementService statementService;

    public StatementController(StatementService statementService) {
        this.statementService = statementService;
    }


    @Operation(summary = "Endpoint for create statement")
    @PostMapping
    public ResponseEntity<StatementResponseDto> register(
            @RequestBody @Valid StatementRequestDto dto) {

        var saved = statementService.register(
                StatementMapper.toEntity(dto),
                dto.getIdTag()
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(StatementMapper.toResponse(saved));
    }


    @Operation(summary = "Endpoint for get statement by filter and pagination")
    @GetMapping
    public ResponseEntity<List<StatementResponseDto>> getByFilterAndPagination(
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
        var statements = statementService
                .getByFilterAndPagination(startDate, endDate, tagId, type, description, page, size);
        if (statements.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(StatementMapper.toResponse(statements));
    }


    @Operation(summary = "Endpoint for get statement by id")
    @GetMapping("/{id}")
    public ResponseEntity<StatementResponseDto> getById(@PathVariable Integer id) {
        var statement = statementService.getById(id);
        return ResponseEntity.ok(StatementMapper.toResponse(statement));
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
}
