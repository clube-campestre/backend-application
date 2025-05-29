package com.campestre.clube.backend_application.controller.mapper;

import com.campestre.clube.backend_application.controller.dtos.requests.SaveTagRequestDto;
import com.campestre.clube.backend_application.controller.dtos.responses.TagResponseDto;
import com.campestre.clube.backend_application.entity.Tag;

public abstract class TagMapper {
    public static Tag toEntity(SaveTagRequestDto dto){
        return new Tag(
                dto.getSurname(), dto.getColor(), dto.getGoal(), dto.getPrivateGoal()
        );
    }

    public static TagResponseDto toResponse(Tag tag){
        return new TagResponseDto(
                tag.getId(), tag.getSurname(), tag.getColor(), tag.getGoal(), tag.getPrivateGoal()
        );
    }
}
