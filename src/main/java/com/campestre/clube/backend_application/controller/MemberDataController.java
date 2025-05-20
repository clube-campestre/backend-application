package com.campestre.clube.backend_application.controller;

import com.campestre.clube.backend_application.controller.dtos.requests.MemberDataDtoRequest;
import com.campestre.clube.backend_application.controller.dtos.responses.MemberDataDtoResponse;
import com.campestre.clube.backend_application.controller.dtos.responses.MemberDataForUnitDtoResponse;
import com.campestre.clube.backend_application.controller.mapper.AddressMapper;
import com.campestre.clube.backend_application.controller.mapper.MemberDataMapper;
import com.campestre.clube.backend_application.entity.MemberData;
import com.campestre.clube.backend_application.entity.enums.ClassCategory;
import com.campestre.clube.backend_application.service.MemberDataService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@RestController
@RequestMapping("/members")
@CrossOrigin("*")
public class MemberDataController {

    @Autowired
    private MemberDataService memberDataService;

    @PostMapping()
    @Operation(summary = "Endpoint for create member")
    public ResponseEntity<MemberDataDtoResponse> register(@RequestBody @Valid MemberDataDtoRequest dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(MemberDataMapper.toResponse(
                memberDataService.register(MemberDataMapper.toEntity(dto))
        ));
    }

    @GetMapping
    @Operation(summary = "Endpoint for list all member data")
    public ResponseEntity<List<MemberDataDtoResponse>> getAll(){
        List<MemberData> members = memberDataService.getAll();
        if(members.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.status(HttpStatus.OK).body(members.stream().map(MemberDataMapper::toResponse).toList());
    }

    @GetMapping("/{cpf}")
    @Operation(summary = "Endpoint for get member data by cpf")
    public ResponseEntity<MemberDataDtoResponse> getById(@PathVariable String cpf){
        return ResponseEntity.status(HttpStatus.OK).body(MemberDataMapper.toResponse(memberDataService.getById(cpf)));
    }

    @GetMapping("unit/{unitId}")
    @Operation(summary = "Endpoint for list member data by unit")
    public ResponseEntity<MemberDataForUnitDtoResponse> getByUnit(@PathVariable Integer unitId){
        Pair<List<MemberData>, Integer> data = memberDataService.getByUnit(unitId);
        return ResponseEntity.status(HttpStatus.OK).body(MemberDataMapper.toResponse(data.a, data.b));
    }

    @GetMapping("class")
    @Operation(summary = "Endpoint for list member data by class")
    public ResponseEntity<List<MemberDataDtoResponse>> getByClass(@RequestBody ClassCategory classCategory) {
        List<MemberData> members = memberDataService.getByClass(classCategory);
        if(members.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.status(HttpStatus.OK).body(members.stream().map(MemberDataMapper::toResponse).toList());
    }

    @PutMapping("/{cpf}")
    @Operation(summary = "Endpoint for update member data by cpf")
    public ResponseEntity<MemberDataDtoResponse> update(@Valid @RequestBody MemberDataDtoRequest dto, @PathVariable String cpf){
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
}
