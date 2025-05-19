package com.campestre.clube.backend_application.controller;

import com.campestre.clube.backend_application.controller.dtos.requests.StatementRequestDto;
import com.campestre.clube.backend_application.controller.dtos.responses.StatementResponseDto;
import com.campestre.clube.backend_application.controller.mapper.StatementMapper;
import com.campestre.clube.backend_application.entity.Statement;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("statements")
@Tag(name = "Statement")
public class StatementController {

    private final StatementService statementService;

    public StatementController(StatementService statementService) {
        this.statementService = statementService;
    }


    @Operation(summary = "Cria um novo statement")
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



    @Operation(summary = "Lista statements com filtros opcionais")
    @GetMapping
    public ResponseEntity<List<StatementResponseDto>> getAll(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime startDate,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime endDate,

            @RequestParam(required = false) Integer tagId,

            @RequestParam(required = false) TransactionType type,

            @RequestParam(required = false) String description) {

        var statements = statementService.getAllFiltered(
                startDate, endDate, tagId, type, description);

        if (statements.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        var response = statements.stream()
                .map(s -> StatementMapper.toResponse((Statement) s))
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }



    @Operation(summary = "Obtém statement por id")
    @GetMapping("/{id}")
    public ResponseEntity<StatementResponseDto> getById(@PathVariable Integer id) {
        var statement = statementService.getById(id);
        return ResponseEntity.ok(StatementMapper.toResponse(statement));
    }



    @Operation(summary = "Atualiza statement por id")
    @PutMapping("/{id}")
    public ResponseEntity<StatementResponseDto> update(
            @RequestBody @Valid StatementRequestDto dto,
            @PathVariable Integer id) {

        var updated = statementService.update(dto, id);
        return ResponseEntity.ok(StatementMapper.toResponse(updated));
    }



    @Operation(summary = "Remove statement por id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        statementService.delete(id);
        return ResponseEntity.ok().build();
    }
}
