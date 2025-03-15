package com.campestre.clube.backend_application.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() { super("User not found");}
    public UserNotFoundException(Integer id) { super("User by id [%s] not found".formatted(id));}
    public UserNotFoundException(String message) {super(message);}
}
