package com.campestre.clube.backend_application.controller;

import com.campestre.clube.backend_application.entity.MedicalData;
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
    public ResponseEntity<MedicalData> register(@RequestBody MedicalData medicalData) {
        return ResponseEntity.status(HttpStatus.CREATED).body(medicalDataService.register(medicalData));
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<MedicalData> getByCpf(@PathVariable String cpf) {
        return ResponseEntity.status(HttpStatus.OK).body(medicalDataService.getById(cpf));
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<MedicalData> update(@PathVariable String cpf, @RequestBody MedicalData medicalData) {
        return ResponseEntity.status(HttpStatus.OK).body(medicalDataService.update(cpf, medicalData));
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> delete(@PathVariable String cpf) {
        medicalDataService.delete(cpf);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
