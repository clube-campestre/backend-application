package com.campestre.clube.backend_application.entity;

import jakarta.persistence.*;

@Entity
public class Unit {
    @Id
    private Integer id;
    private String surname;
    private Integer score = 0;

    public Unit(Integer id, String surname, Integer score) {
        this.id = id;
        this.surname = surname;
        this.score = score;
    }

    public Unit(String surname, Integer score) {
        this.surname = surname;
        this.score = score;
    }

    public Unit() {
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
