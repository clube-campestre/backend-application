package com.campestre.clube.backend_application.controller;

import com.campestre.clube.backend_application.controller.dtos.requests.SaveMedicalDataRequestDto;
import com.campestre.clube.backend_application.controller.dtos.responses.GetMedicalDataResponseDto;
import com.campestre.clube.backend_application.controller.dtos.responses.SaveMedicalDataResponseDto;
import com.campestre.clube.backend_application.service.MedicalDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medical-data")
public class MedicalDataController {

    @Autowired
    private MedicalDataService medicalDataService;

    @PostMapping("/register")
    public ResponseEntity<SaveMedicalDataResponseDto> register(@RequestBody SaveMedicalDataRequestDto medicalData) {
        return ResponseEntity.status(HttpStatus.CREATED).body(SaveMedicalDataResponseDto.toResponse(
                medicalDataService.register(SaveMedicalDataRequestDto.toEntity(medicalData))
        ));
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<GetMedicalDataResponseDto> getByCpf(@PathVariable String cpf) {
        return ResponseEntity.status(HttpStatus.OK).body(GetMedicalDataResponseDto.toResponse(
                medicalDataService.getById(cpf)
        ));
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<SaveMedicalDataResponseDto> update(
            @PathVariable String cpf, @RequestBody SaveMedicalDataRequestDto medicalData
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(SaveMedicalDataResponseDto.toResponse(
                medicalDataService.update(cpf, SaveMedicalDataRequestDto.toEntity(medicalData))
        ));
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> delete(@PathVariable String cpf) {
        medicalDataService.delete(cpf);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
