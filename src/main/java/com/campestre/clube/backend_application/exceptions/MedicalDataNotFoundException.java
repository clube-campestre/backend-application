package com.campestre.clube.backend_application.exceptions;

public class MedicalDataNotFoundException extends RuntimeException {
    public MedicalDataNotFoundException() { super("Medical data not found");}
    public MedicalDataNotFoundException(Integer id) { super("Medical data by id [%s] not found".formatted(id));}
    public MedicalDataNotFoundException(String message) {super(message);}
}
