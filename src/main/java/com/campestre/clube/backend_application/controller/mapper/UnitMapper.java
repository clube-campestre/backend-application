package com.campestre.clube.backend_application.controller.mapper;

import com.campestre.clube.backend_application.controller.dtos.requests.GetUnitRequestDto;
import com.campestre.clube.backend_application.controller.dtos.requests.UnitRequestDto;
import com.campestre.clube.backend_application.controller.dtos.responses.UnitResetedResponseDto;
import com.campestre.clube.backend_application.controller.dtos.responses.UnitResponseDto;
import com.campestre.clube.backend_application.entity.Unit;
import org.antlr.v4.runtime.misc.Pair;

import java.util.List;

public class UnitMapper {
    public static Unit toEntity(UnitRequestDto dto){
        return new Unit(dto.getSurname(), dto.getScore());
    }

    public static UnitResponseDto toResponse(Unit unit){
        return new UnitResponseDto(unit.getId(), unit.getSurname(), unit.getScore());
    }

    public static List<UnitResponseDto> toResponse(List<Unit> units){
        return units.stream().map(UnitMapper::toResponse).toList();
    }

    public static Unit toEntity(GetUnitRequestDto dto){
        Unit unit = new Unit();
        unit.setId(dto.getId());
        return unit;
    }

    public static UnitResetedResponseDto toResponse(Pair<List<String>, List<String>> pair){
        return new UnitResetedResponseDto(pair.a, pair.b);
    }
}
