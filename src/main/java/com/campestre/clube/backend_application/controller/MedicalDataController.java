package com.campestre.clube.backend_application.controller;

import com.campestre.clube.backend_application.model.MedicalData;
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

   @GetMapping("/{id}")
    public ResponseEntity<MedicalData> getById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(medicalDataService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicalData> update(@PathVariable Integer id, @RequestBody MedicalData medicalData) {
        return ResponseEntity.status(HttpStatus.OK).body(medicalDataService.update(id, medicalData));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        medicalDataService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
