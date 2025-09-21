package com.campestre.clube.backend_application.infrastructure.persistence.jpa.tag;

import com.campestre.clube.backend_application.core.domain.Address;
import com.campestre.clube.backend_application.core.domain.Tag;
import com.campestre.clube.backend_application.core.domain.Transport;
import com.campestre.clube.backend_application.infrastructure.persistence.jpa.transport.TransportEntity;
import com.campestre.clube.backend_application.infrastructure.persistence.jpa.transport.TransportEntityMapper;
import com.campestre.clube.backend_application.infrastructure.web.dtos.address.AddressResponseDto;
import com.campestre.clube.backend_application.infrastructure.web.dtos.tag.TagResponseDto;
import com.campestre.clube.backend_application.infrastructure.web.mappers.AddressDtoMapper;

import java.util.List;
import java.util.stream.Collectors;

public class TagEntityMapper {
    public static TagEntity toEntity(Tag domain) {
        if (domain == null) return null;
        TagEntity entity = new TagEntity();
        entity.setId(domain.getId());
        entity.setSurname(domain.getSurname());
        entity.setColor(domain.getColor());
        entity.setGoal(domain.getGoal());
        entity.setPrivateGoal(domain.getPrivateGoal());
        return entity;
    }

    public static Tag toDomain(TagEntity entity) {
        if (entity == null) return null;
        return Tag.of(
                entity.getId(),
                entity.getSurname(),
                entity.getColor(),
                entity.getGoal(),
                entity.getPrivateGoal()
        );
    }

    public static TagResponseDto toResponse(Tag tag) {
        return new TagResponseDto(
                tag.getId(),
                tag.getSurname(),
                tag.getColor(),
                tag.getGoal(),
                tag.getPrivateGoal()
        );
    }

    public static List<Tag> toDomain(List<TagEntity> entities) {
        if (entities == null) return null;
        return entities.stream().map(TagEntityMapper::toDomain).collect(Collectors.toList());
    }
}
