package com.campestre.clube.backend_application.core.domain;

import java.math.BigDecimal;

public class Tag {
    private Long id;
    private String surname;
    private String color;
    private BigDecimal goal;
    private Boolean privateGoal;

    private Tag(Long id, String surname , String color , BigDecimal goal , Boolean privateGoal) {
        this.id = id;
        this.surname = surname;
        this.color = color;
        this.goal = goal;
        this.privateGoal = privateGoal;
    }

    public Tag() {}

    public static Tag of(Long id, String surname, String color, BigDecimal goal, Boolean privateGoal) {
        return new Tag(
                id,
                surname,
                color,
                goal,
                privateGoal
        );
    }

    public static Tag of(String surname, String color, BigDecimal goal, Boolean privateGoal) {
        return new Tag(
                null,
                surname,
                color,
                goal,
                privateGoal
        );
    }

    public Long getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public String getColor() {
        return color;
    }

    public BigDecimal getGoal() {
        return goal;
    }

    public Boolean getPrivateGoal() {
        return privateGoal;
    }
}
