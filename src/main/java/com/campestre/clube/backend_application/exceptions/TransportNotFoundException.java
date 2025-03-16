package com.campestre.clube.backend_application.exceptions;

public class TransportNotFoundException extends RuntimeException{
    public TransportNotFoundException() { super("Transport not found");}
    public TransportNotFoundException(Integer id) { super("Transport by id [%s] not found".formatted(id));}
    public TransportNotFoundException(String message) { super(message);}
}
