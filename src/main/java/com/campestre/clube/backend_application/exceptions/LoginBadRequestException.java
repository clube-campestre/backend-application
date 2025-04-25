package com.campestre.clube.backend_application.exceptions;

public class LoginBadRequestException extends RuntimeException {
    public LoginBadRequestException() { super("Incorrect email or password");}
    public LoginBadRequestException(String message) {super(message);}
}
