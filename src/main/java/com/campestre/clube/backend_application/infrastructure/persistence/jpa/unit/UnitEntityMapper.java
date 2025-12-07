package com.campestre.clube.backend_application.infrastructure.persistence.jpa.unit;

import com.campestre.clube.backend_application.core.domain.Unit;
import com.campestre.clube.backend_application.infrastructure.web.dtos.unit.UnitResponseDto;

import java.util.List;
import java.util.stream.Collectors;

public class UnitEntityMapper {
    public static UnitEntity toEntity(Unit domain) {
        if (domain == null) return null;
        UnitEntity entity = new UnitEntity();
        entity.setId(domain.getId());
        entity.setSurname(domain.getSurname());
        entity.setScore(domain.getScore());
        entity.setHasRanking(domain.getHasRanking());
        return entity;
    }

    public static Unit toDomain(UnitEntity entity) {
        if (entity == null) return null;
        return Unit.of(
                entity.getId(),
                entity.getSurname(),
                entity.getScore(),
                entity.getHasRanking()
        );
    }

    public static UnitResponseDto toResponse(Unit domain) {
        if (domain == null) return null;
        return new UnitResponseDto(
                domain.getId(),
                domain.getSurname(),
                domain.getScore()
        );
    }

    public static List<Unit> toDomain(List<UnitEntity> entities) {
        if (entities == null) return null;
        return entities.stream().map(UnitEntityMapper::toDomain).collect(Collectors.toList());
    }
}
