package com.campestre.clube.backend_application.infrastructure.web;

import com.campestre.clube.backend_application.core.application.unit.*;
import com.campestre.clube.backend_application.core.application.unit.command.ChangeUnitScoreCommand;
import com.campestre.clube.backend_application.core.application.unit.command.UpdateUnitScoreCommand;
import com.campestre.clube.backend_application.core.domain.Unit;
import com.campestre.clube.backend_application.infrastructure.web.dtos.unit.UnitResponseDto;
import com.campestre.clube.backend_application.infrastructure.web.mappers.UnitDtoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/units")
@CrossOrigin("*")
@Tag(name = "Unit Controller", description = "Unit data routes")
public class UnitController {
    private final ChangeUnitScoreUseCase changeUnitScoreUseCase;
    private final GetUnitRankingUseCase getUnitRankingUseCase;
    private final ResetUnitScoresUseCase resetUnitScoresUseCase;
    private final UpdateUnitScoreUseCase updateUnitScoreUseCase;

    public UnitController(
            ChangeUnitScoreUseCase changeUnitScoreUseCase,
            GetUnitRankingUseCase getUnitRankingUseCase,
            ResetUnitScoresUseCase resetUnitScoresUseCase,
            UpdateUnitScoreUseCase updateUnitScoreUseCase
    ) {
        this.changeUnitScoreUseCase = changeUnitScoreUseCase;
        this.getUnitRankingUseCase = getUnitRankingUseCase;
        this.resetUnitScoresUseCase = resetUnitScoresUseCase;
        this.updateUnitScoreUseCase = updateUnitScoreUseCase;
    }

    @PutMapping("/score")
    @Operation(summary = "Endpoint for update unit score by unit id")
    public ResponseEntity<UnitResponseDto> updateScoreById(@RequestParam String surname, @RequestParam Integer newScore) {
        return ResponseEntity.status(HttpStatus.OK).body(
                UnitDtoMapper.toResponse(updateUnitScoreUseCase.execute(new UpdateUnitScoreCommand(surname, newScore)))
        );
    }

    @PostMapping("/score")
    @Operation(summary = "Endpoint for increase or descrease unit score by unit id")
    public ResponseEntity<UnitResponseDto> increaseOrDecreaseTheScoreById(
            @RequestParam String surname, @RequestParam Integer score, @RequestParam Boolean isSum
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(UnitDtoMapper.toResponse(changeUnitScoreUseCase.execute(
                new ChangeUnitScoreCommand(surname, score, isSum)
        )));
    }

    @PostMapping("/reseted")
    @Operation(summary = "Endpoint for reset all unit score")
    public ResponseEntity<Boolean> resetAllScores() {
        return ResponseEntity.status(HttpStatus.OK).body(resetUnitScoresUseCase.execute());
    }

    @GetMapping("/ranking")
    @Operation(summary = "Endpoint for get units by ranking")
    public ResponseEntity<List<UnitResponseDto>> getRanked() {
        return ResponseEntity.status(HttpStatus.OK).body(UnitDtoMapper.toResponse(getUnitRankingUseCase.execute()));
    }
}
