package com.campestre.clube.backend_application.controller;

import com.campestre.clube.backend_application.service.EnumerationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/enumerations")
@CrossOrigin("*")
@Tag(name = "Enumeration Controller", description = "Enumeration data routes")
public class EnumerationController {

    @Autowired
    public EnumerationService enumerationService;

    @Operation(summary = "Endpoint for list all classes")
    @GetMapping("/class")
    public ResponseEntity<List<String>> getAllClassesCategory() {
        List<String> classesCategory = enumerationService.getAllClassesCategory();
        if(classesCategory.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.status(HttpStatus.OK).body(classesCategory);
    }

    @Operation(summary = "Endpoint for list all classes role")
    @GetMapping("/class-role")
    public ResponseEntity<List<String>> getAllClassRole() {
        List<String> classesRole = enumerationService.getAllClassesRole();
        if(classesRole.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.status(HttpStatus.OK).body(classesRole);
    }

    @Operation(summary = "Endpoint for list all units")
    @GetMapping("/unit")
    public ResponseEntity<List<String>> getAllUnit() {
        List<String> units = enumerationService.getAllUnits();
        if(units.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.status(HttpStatus.OK).body(units);
    }

    @Operation(summary = "Endpoint for list all units role")
    @GetMapping("/unit-role")
    public ResponseEntity<List<String>> getAllUnitsRole() {
        List<String> unitsRole = enumerationService.getAllUnitsRole();
        if(unitsRole.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.status(HttpStatus.OK).body(unitsRole);
    }
}