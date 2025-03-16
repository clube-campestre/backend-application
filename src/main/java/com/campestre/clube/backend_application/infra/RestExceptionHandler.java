package com.campestre.clube.backend_application.infra;

import com.campestre.clube.backend_application.exceptions.*;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice

public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<RestErrorMessage> userNotFoundHandler(UserNotFoundException exception){
        return notFound(exception.getMessage());
    }

    @ExceptionHandler(LoginBadRequestException.class)
    private ResponseEntity<RestErrorMessage> loginBadRequestHandler(LoginBadRequestException exception){
        return badRequest(exception.getMessage());
    }

    @ExceptionHandler(EmailConfictException.class)
    private ResponseEntity<RestErrorMessage> emailConflictHandler(EmailConfictException exception){
        return conflict(exception.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    private ResponseEntity<RestErrorMessage> badRequestHandler(BadRequestException exception){
        return badRequest(exception.getMessage());
    }

    @ExceptionHandler(MedicalDataConfictException.class)
    private ResponseEntity<RestErrorMessage> medicalDataConflictHandler(MedicalDataConfictException exception){
        return conflict(exception.getMessage());
    }

    @ExceptionHandler(MedicalDataNotFoundException.class)
    private ResponseEntity<RestErrorMessage> userNotFoundHandler(MedicalDataNotFoundException exception){
        return notFound(exception.getMessage());
    }

    @ExceptionHandler(EnterpriseConfictException.class)
    private ResponseEntity<RestErrorMessage> enterpriseConflictHandler(EnterpriseConfictException exception){
        return conflict(exception.getMessage());
    }


    private ResponseEntity<RestErrorMessage> badRequest(String exceptionMessage) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RestErrorMessage(HttpStatus.BAD_REQUEST, exceptionMessage));
    }

    private ResponseEntity<RestErrorMessage> conflict(String exceptionMessage) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new RestErrorMessage(HttpStatus.CONFLICT, exceptionMessage));
    }

    private ResponseEntity<RestErrorMessage> notFound(String exceptionMessage) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RestErrorMessage(HttpStatus.NOT_FOUND, exceptionMessage));
    }
}
