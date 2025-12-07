package com.campestre.clube.backend_application.infrastructure.web.dtos.unit;

public class UnitResponseDto {
    private Long id;
    private String surname;
    private Integer score;

    public UnitResponseDto(Long id, String surname, Integer score) {
        this.id = id;
        this.surname = surname;
        this.score = score;
    }

    public UnitResponseDto() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
