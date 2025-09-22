package com.campestre.clube.backend_application.core.domain.valueobject;

import com.campestre.clube.backend_application.core.application.exceptions.BadRequestException;

public class MedicalProblem {
    private Boolean haveProblem;
    private String medication;

    private MedicalProblem(Boolean haveProblem, String medication) {
        this.haveProblem = haveProblem;
        this.medication = medication;
    }

    public static MedicalProblem of(Boolean haveProblem, String medication) {
        if (!haveProblem && !medication.isEmpty())
            throw new BadRequestException("O membro não pode ter medicação de um problema que ele não tem");
        return new MedicalProblem(haveProblem, medication);
    }

    public Boolean getHaveProblem() {
        return haveProblem;
    }

    public String getMedication() {
        return medication;
    }
}
