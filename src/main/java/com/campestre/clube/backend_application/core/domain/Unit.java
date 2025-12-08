package com.campestre.clube.backend_application.core.domain;

public class Unit {
    private Long id;
    private String surname;
    private Integer score;
    private Boolean hasRanking;

    private Unit(Long id, String surname, Integer score, Boolean hasRanking) {
        this.id = id;
        this.surname = surname;
        this.score = score;
        this.hasRanking = hasRanking;
    }

    public Unit() {}

    public static Unit of(Long id, String surname, Integer score, Boolean hasRanking) {
        return new Unit(id, surname, score, hasRanking);
    }

    public static Unit of(Long id, String surname, Boolean hasRanking) {
        return new Unit(id, surname, 0, hasRanking);
    }

    public Long getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public Integer getScore() {
        return score;
    }

    public Boolean getHasRanking() {
        return hasRanking;
    }
}
