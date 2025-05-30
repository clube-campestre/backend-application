package com.campestre.clube.backend_application.controller;

import com.campestre.clube.backend_application.controller.dtos.responses.UnitResetedResponseDto;
import com.campestre.clube.backend_application.controller.dtos.responses.UnitResponseDto;
import com.campestre.clube.backend_application.controller.mapper.UnitMapper;
import com.campestre.clube.backend_application.service.UnitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/units")
@CrossOrigin("*")
@Tag(name = "Unit Controller", description = "Unit data routes")
public class UnitController {

    @Autowired
    private UnitService unitService;

    @PutMapping("/score")
    @Operation(summary = "Endpoint for update unit score by unit name")
    public ResponseEntity<UnitResponseDto> updateScoreById(@RequestParam String unitName, @RequestParam Integer newScore) {
        return ResponseEntity.status(HttpStatus.OK).body(
                UnitMapper.toResponse(unitService.updateByUnitName(unitName, newScore))
        );
    }

    @PostMapping("/reseted")
    @Operation(summary = "Endpoint for reset all unit score")
    public ResponseEntity<UnitResetedResponseDto> resetAllScores() {
        return ResponseEntity.status(HttpStatus.OK).body(UnitMapper.toResponse(unitService.resetAllScores()));
    }

    @GetMapping("/ranking")
    @Operation(summary = "Endpoint for get units by ranking")
    public ResponseEntity<List<UnitResponseDto>> getRanked() {
        return ResponseEntity.status(HttpStatus.OK).body(UnitMapper.toResponse(unitService.getRanked()));
    }
}