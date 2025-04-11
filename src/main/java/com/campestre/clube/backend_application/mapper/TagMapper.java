package com.campestre.clube.backend_application.mapper;

import com.campestre.clube.backend_application.controller.dtos.requests.TagRequestDto;
import com.campestre.clube.backend_application.entity.Tag;

public class TagMapper {
    public static Tag toEntity(TagRequestDto dto){
        Tag tag = new Tag();
        tag.setSurname(dto.getSurname());
        tag.setColor(dto.getColor());
        return tag;
    }
}
