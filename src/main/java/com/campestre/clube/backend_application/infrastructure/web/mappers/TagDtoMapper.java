package com.campestre.clube.backend_application.infrastructure.web.mappers;

import com.campestre.clube.backend_application.core.application.tag.command.SaveTagCommand;
import com.campestre.clube.backend_application.core.application.tag.command.UpdateTagCommand;
import com.campestre.clube.backend_application.core.domain.Tag;
import com.campestre.clube.backend_application.infrastructure.web.dtos.tag.TagRequestDto;
import com.campestre.clube.backend_application.infrastructure.web.dtos.tag.TagResponseDto;

import java.util.List;
import java.util.stream.Collectors;

public class TagDtoMapper {

    public static SaveTagCommand toCommand(TagRequestDto dto) {
        return new SaveTagCommand(
                dto.getSurname(),
                dto.getColor(),
                dto.getGoal(),
                dto.getPrivateGoal()
        );
    }

    public static UpdateTagCommand toCommand(TagRequestDto dto, Long id) {
        return new UpdateTagCommand(
                id,
                dto.getSurname(),
                dto.getColor(),
                dto.getGoal(),
                dto.getPrivateGoal()
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

    public static List<TagResponseDto> toResponse(List<Tag> tags) {
        return tags.stream().map(TagDtoMapper::toResponse).collect(Collectors.toList());
    }
}