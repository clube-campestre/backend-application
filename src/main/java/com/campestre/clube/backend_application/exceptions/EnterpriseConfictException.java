package com.campestre.clube.backend_application.exceptions;

public class EnterpriseConfictException extends RuntimeException {
    public EnterpriseConfictException() { super("Enterprise already registered");}
}
