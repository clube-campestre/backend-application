package com.campestre.clube.backend_application.controller.mapper;

import com.campestre.clube.backend_application.controller.dtos.requests.SaveTagRequestDto;
import com.campestre.clube.backend_application.controller.dtos.requests.TagRequestDto;
import com.campestre.clube.backend_application.controller.dtos.responses.TagResponseDto;
import com.campestre.clube.backend_application.entity.Tag;

public abstract class TagMapper {
    public static Tag toEntity(TagRequestDto dto){
        Tag tag = new Tag();
        tag.setSurname(dto.getSurname());
        tag.setColor(dto.getColor());
        return tag;
    }

    public static TagResponseDto toResponse(Tag tag){
        TagResponseDto response = new TagResponseDto();
        response.setId(tag.getId());
        response.setSurname(tag.getSurname());
        response.setColor(tag.getColor());
        return response;
    }
}
