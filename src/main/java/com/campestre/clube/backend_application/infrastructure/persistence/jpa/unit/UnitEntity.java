package com.campestre.clube.backend_application.infrastructure.persistence.jpa.unit;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "unit")
public class UnitEntity {
    @Id
    private Long id;
    private String surname;
    private Integer score = 0;
    private Boolean hasRanking;

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

    public Boolean getHasRanking() {
        return hasRanking;
    }

    public void setHasRanking(Boolean hasRanking) {
        this.hasRanking = hasRanking;
    }
}
