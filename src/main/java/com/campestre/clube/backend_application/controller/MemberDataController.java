package com.campestre.clube.backend_application.controller;

import com.campestre.clube.backend_application.controller.dtos.requests.MemberDataDtoRequest;
import com.campestre.clube.backend_application.controller.dtos.responses.*;
import com.campestre.clube.backend_application.controller.mapper.MemberDataMapper;
import com.campestre.clube.backend_application.controller.mapper.StatementMapper;
import com.campestre.clube.backend_application.entity.MemberData;
import com.campestre.clube.backend_application.entity.Pagination;
import com.campestre.clube.backend_application.entity.Statement;
import com.campestre.clube.backend_application.entity.enums.ClassCategory;
import com.campestre.clube.backend_application.entity.enums.TransactionType;
import com.campestre.clube.backend_application.service.MemberDataService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.antlr.v4.runtime.misc.Pair;
import org.antlr.v4.runtime.misc.Triple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/members")
@CrossOrigin("*")
public class MemberDataController {

    @Autowired
    private MemberDataService memberDataService;

    @PostMapping()
    @Operation(summary = "Endpoint for create member")
    public ResponseEntity<MemberDataResponseDto> register(@RequestBody @Valid MemberDataDtoRequest dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(MemberDataMapper.toResponse(
                memberDataService.register(MemberDataMapper.toEntity(dto))
        ));
    }

    @GetMapping
    @Operation(summary = "Endpoint for list all member data")
    public ResponseEntity<List<MemberDataResponseDto>> getAll(){
        List<MemberData> members = memberDataService.getAll();
        if(members.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.status(HttpStatus.OK).body(members.stream().map(MemberDataMapper::toResponse).toList());
    }

    @GetMapping("/{cpf}")
    @Operation(summary = "Endpoint for get member data by cpf")
    public ResponseEntity<MemberDataResponseDto> getById(@PathVariable String cpf){
        return ResponseEntity.status(HttpStatus.OK).body(MemberDataMapper.toResponse(memberDataService.getById(cpf)));
    }

    @GetMapping("unit/{unitId}")
    @Operation(summary = "Endpoint for list member data by unit")
    public ResponseEntity<MemberDataForUnitDtoResponse> getByUnit(@PathVariable Integer unitId){
        Triple<List<MemberData>, Integer, String> data = memberDataService.getByUnit(unitId);
        return ResponseEntity.status(HttpStatus.OK).body(MemberDataMapper.toResponse(data.a, data.b, data.c));
    }

    @GetMapping("/class")
    @Operation(summary = "Endpoint for list member data by class")
    public ResponseEntity<MemberDataForClassDtoResponse> getByClass(@RequestParam ClassCategory classCategory) {
        Pair<List<MemberData>, String> data = memberDataService.getByClass(classCategory);
        return ResponseEntity.status(HttpStatus.OK).body(MemberDataMapper.toResponse(data.a, data.b));
    }

    @PutMapping("/{cpf}")
    @Operation(summary = "Endpoint for update member data by cpf")
    public ResponseEntity<MemberDataResponseDto> update(@Valid @RequestBody MemberDataDtoRequest dto, @PathVariable String cpf){
        return ResponseEntity.status(HttpStatus.OK).body(MemberDataMapper.toResponse(
                memberDataService.update(cpf, MemberDataMapper.toEntity(dto))
        ));
    }

    @DeleteMapping("/{cpf}")
    @Operation(summary = "Endpoint for remove member data by cpf")
    public ResponseEntity<Void> delete(@PathVariable String cpf) {
        memberDataService.delete(cpf);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Endpoint for get member data by filter and pagination")
    @GetMapping("/filter")
    public ResponseEntity<GetByFilterAndPaginationMemberDataResponseDto> getByFilterAndPagination(
            @RequestParam(required = false)
            String unit,
            @RequestParam(required = false)
            String classCategory,
            @RequestParam(required = false)
            String name,

            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        Pair<List<MemberData>, Pagination> data = memberDataService
                .getByFilterAndPagination(unit, classCategory, name, page, size);
        return ResponseEntity.ok(MemberDataMapper.toResponse(
                data.a,
                data.b.getPageNumber(),
                data.b.getPageSize(),
                data.b.getTotalItems(),
                data.b.getTotalPages()
        ));
    }
}
