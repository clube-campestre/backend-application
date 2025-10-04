package com.campestre.clube.backend_application.core.domain.valueobject;

import static com.campestre.clube.backend_application.core.exceptions.ExceptionExtensions.BAD_REQUEST_MEDICAL_PROBLEM;

public class MedicalProblem {
    private Boolean haveProblem;
    private String medication;

    private MedicalProblem(Boolean haveProblem, String medication) {
        this.haveProblem = haveProblem;
        this.medication = medication;
    }

    public static MedicalProblem of(Boolean haveProblem, String medication) {
        if (!haveProblem && !medication.isEmpty()) throw BAD_REQUEST_MEDICAL_PROBLEM;
        return new MedicalProblem(haveProblem, medication);
    }

    public Boolean getHaveProblem() {
        return haveProblem;
    }

    public String getMedication() {
        return medication;
    }
}
