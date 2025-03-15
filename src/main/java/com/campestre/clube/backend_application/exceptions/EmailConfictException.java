package com.campestre.clube.backend_application.exceptions;

public class EmailConfictException extends RuntimeException {
    public EmailConfictException() { super("User with existing email");}
    public EmailConfictException(String message) {super(message);}
}
