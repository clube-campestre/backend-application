package com.campestre.clube.backend_application.infrastructure.persistence.jpa.statement;

import com.campestre.clube.backend_application.core.domain.Statement;
import com.campestre.clube.backend_application.infrastructure.persistence.jpa.tag.TagEntityMapper;

import java.util.List;
import java.util.stream.Collectors;

public class StatementEntityMapper {
    public static StatementEntity toEntity(Statement domain) {
        if (domain == null) return null;
        StatementEntity entity = new StatementEntity();
        entity.setId(domain.getId());
        entity.setInformation(domain.getInformation());
        entity.setPrice(domain.getPrice());
        entity.setTransactionDate(domain.getTransactionDate());
        entity.setTransactionType(domain.getTransactionType());
        entity.setTag(TagEntityMapper.toEntity(domain.getTag()));
        return entity;
    }

    public static Statement toDomain(StatementEntity entity) {
        if (entity == null) return null;
        return Statement.of(
                entity.getId(),
                entity.getInformation(),
                entity.getPrice(),
                entity.getTransactionDate(),
                entity.getTransactionType(),
                TagEntityMapper.toDomain(entity.getTag())
        );
    }

    public static List<Statement> toDomain(List<StatementEntity> entities) {
        if (entities == null) return null;
        return entities.stream().map(StatementEntityMapper::toDomain).collect(Collectors.toList());
    }
}
