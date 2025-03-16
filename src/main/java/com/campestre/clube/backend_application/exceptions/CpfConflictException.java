package com.campestre.clube.backend_application.exceptions;

public class CpfConflictException extends RuntimeException{
    public CpfConflictException() { super("CPF already registered");}
}
