package com.campestre.clube.backend_application.controller.dtos.responses;

public class UnitResponseDto {
    private Integer id;
    private String surname;
    private Integer score;

    public UnitResponseDto() {
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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
