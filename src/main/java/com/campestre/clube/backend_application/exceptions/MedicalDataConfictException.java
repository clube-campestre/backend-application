package com.campestre.clube.backend_application.exceptions;

public class MedicalDataConfictException extends RuntimeException {
    public MedicalDataConfictException() { super("Medical data already exists");}
    public MedicalDataConfictException(Integer id) { super("Medical data with this id [%s] already exists".formatted(id));}
    public MedicalDataConfictException(String message) {super(message);}
}
