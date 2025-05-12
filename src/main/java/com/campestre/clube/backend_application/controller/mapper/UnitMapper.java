package com.campestre.clube.backend_application.controller.mapper;

import com.campestre.clube.backend_application.controller.dtos.requests.UnitRequestDto;
import com.campestre.clube.backend_application.controller.dtos.responses.UnitResponseDto;
import com.campestre.clube.backend_application.entity.Unit;

public class UnitMapper {
    public static Unit toEntity(UnitRequestDto dto){
        Unit unit = new Unit();
        unit.setSurname(dto.getSurname());
        unit.setScore(dto.getScore());
        return unit;
    }

    public static Unit toEntity(Integer unitId){
        Unit unit = new Unit();
        unit.setId(unitId);
        return unit;
    }

    public static UnitResponseDto toResponse(Unit unit){
        UnitResponseDto response = new UnitResponseDto();
        response.setId(unit.getId());
        response.setSurname(unit.getSurname());
        response.setScore(unit.getScore());
        return response;
    }
}
