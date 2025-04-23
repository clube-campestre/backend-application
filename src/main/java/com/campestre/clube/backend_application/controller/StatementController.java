package com.campestre.clube.backend_application.controller;

import com.campestre.clube.backend_application.controller.dtos.requests.StatementRequestDto;
import com.campestre.clube.backend_application.controller.dtos.responses.StatementResponseDto;
import com.campestre.clube.backend_application.entity.Statement;
import com.campestre.clube.backend_application.controller.mapper.StatementMapper;
import com.campestre.clube.backend_application.service.StatementService;
import com.campestre.clube.backend_application.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/statements")
@CrossOrigin("*")
@Tag(name = "Statement Controller", description = "Statement data routes")
public class StatementController {

    @Autowired
    private StatementService statementService;

    @Autowired
    private TagService tagService;

    @Operation(summary = "Endpoint for create statement")
    @PostMapping
    public ResponseEntity<StatementResponseDto> register(@RequestBody @Valid StatementRequestDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(StatementMapper.toResponse(
                statementService.register(StatementMapper.toEntity(dto), dto.getIdTag())
        ));
    }

    @Operation(summary = "Endpoint for list all statements")
    @GetMapping
    public ResponseEntity<List<StatementResponseDto>> getAll(){
        List<Statement> statements = statementService.getAll();
        if(statements.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.status(HttpStatus.OK).body(statements.stream().map(StatementMapper::toResponse)
                .toList());
    }

    @Operation(summary = "Endpoint for get statement by id")
    @GetMapping("/{id}")
    public ResponseEntity<StatementResponseDto> getById(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(StatementMapper.toResponse(statementService.getById(id)));
    }

    @Operation(summary = "Endpoint for update statement by id")
    @PutMapping("/{id}")
    public ResponseEntity<StatementResponseDto> update(@Valid @RequestBody StatementRequestDto dto, @PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(StatementMapper.toResponse(
                statementService.update(dto, id)
        ));
    }

    @Operation(summary = "Endpoint for remove statement by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        statementService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
