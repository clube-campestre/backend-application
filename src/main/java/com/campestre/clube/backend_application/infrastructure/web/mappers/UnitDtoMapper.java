package com.campestre.clube.backend_application.infrastructure.web.mappers;

import com.campestre.clube.backend_application.core.application.unit.command.ChangeUnitScoreCommand;
import com.campestre.clube.backend_application.core.application.unit.command.UpdateUnitScoreCommand;
import com.campestre.clube.backend_application.core.domain.Unit;
import com.campestre.clube.backend_application.infrastructure.web.dtos.unit.UnitResponseDto;

import java.util.List;
import java.util.stream.Collectors;

public class UnitDtoMapper {

    public static ChangeUnitScoreCommand toCommand(String surname, Integer score, Boolean isSum) {
        return new ChangeUnitScoreCommand(surname, score, isSum);
    }

    public static UpdateUnitScoreCommand toCommand(String surname, Integer score) {
        return new UpdateUnitScoreCommand(surname, score);
    }

    public static UnitResponseDto toResponse(Unit unit) {
        return new UnitResponseDto(
                unit.getId(),
                unit.getSurname(),
                unit.getScore()
        );
    }

    public static List<UnitResponseDto> toResponse(List<Unit> units) {
        return units.stream().map(UnitDtoMapper::toResponse).collect(Collectors.toList());
    }
}