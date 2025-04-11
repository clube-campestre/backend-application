package com.campestre.clube.backend_application.controller.dtos.responses;
import com.campestre.clube.backend_application.entity.Tag;

public class TagResponseDto {
    private Integer id;
    private String surname;
    private String color;


    public static TagResponseDto toResponse(Tag tag){
        TagResponseDto response = new TagResponseDto();
        response.setId(tag.getId());
        response.setSurname(tag.getSurname());
        response.setColor(tag.getColor());
        return response;
    }

    public TagResponseDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
