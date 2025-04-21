package com.campestre.clube.backend_application.controller;

import com.campestre.clube.backend_application.controller.dtos.requests.SaveMedicalDataRequestDto;
import com.campestre.clube.backend_application.controller.dtos.responses.GetMedicalDataResponseDto;
import com.campestre.clube.backend_application.controller.dtos.responses.SaveMedicalDataResponseDto;
import com.campestre.clube.backend_application.controller.mapper.MedicalDataMapper;
import com.campestre.clube.backend_application.service.MedicalDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medical-data")
@CrossOrigin("*")
@Tag(name = "Medical data Controller", description = "Medical data routes")
public class MedicalDataController {

    @Autowired
    private MedicalDataService medicalDataService;

    @Operation(summary = "Endpoint for create medical data")
    @PostMapping("/register")
    public ResponseEntity<SaveMedicalDataResponseDto> register(@RequestBody SaveMedicalDataRequestDto medicalData) {
        return ResponseEntity.status(HttpStatus.CREATED).body(MedicalDataMapper.toSaveResponse(
                medicalDataService.register(MedicalDataMapper.toEntity(medicalData))
        ));
    }

    @Operation(summary = "Endpoint for get medical data by cpf")
    @GetMapping("/{cpf}")
    public ResponseEntity<GetMedicalDataResponseDto> getByCpf(@PathVariable String cpf) {
        return ResponseEntity.status(HttpStatus.OK).body(MedicalDataMapper.toGetResponse(
                medicalDataService.getById(cpf)
        ));
    }

    @Operation(summary = "Endpoint for update medical data by cpf")
    @PutMapping("/{cpf}")
    public ResponseEntity<SaveMedicalDataResponseDto> update(
            @PathVariable String cpf, @RequestBody SaveMedicalDataRequestDto medicalData
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(MedicalDataMapper.toSaveResponse(
                medicalDataService.update(cpf, MedicalDataMapper.toEntity(medicalData))
        ));
    }

    @Operation(summary = "Endpoint for remove medical data by cpf")
    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> delete(@PathVariable String cpf) {
        medicalDataService.delete(cpf);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
