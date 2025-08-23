package com.campestre.clube.backend_application.core.domain;

public class Unit {
    private Integer id;
    private String surname;
    private Integer score;

    private Unit(Integer id, String surname, Integer score) {
        this.id = id;
        this.surname = surname;
        this.score = score;
    }

    public static Unit of(Integer id, String surname) {
        return new Unit(
                id,
                surname,
                0
        );
    }

    public Integer getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
