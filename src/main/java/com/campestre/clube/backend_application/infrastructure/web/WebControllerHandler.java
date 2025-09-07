package com.campestre.clube.backend_application.infrastructure.web;

import com.campestre.clube.backend_application.core.application.exceptions.BadRequestException;
import com.campestre.clube.backend_application.core.application.exceptions.InternalServerException;
import com.campestre.clube.backend_application.core.application.exceptions.NotFoundException;
import com.campestre.clube.backend_application.core.application.exceptions.ConflictException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WebControllerHandler {
    public record RestErrorMessage(HttpStatus status, String message){}

    private ResponseEntity<RestErrorMessage> buildResponse(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(new RestErrorMessage(status, message));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<RestErrorMessage> notFoundHandler(NotFoundException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<RestErrorMessage> badRequestHandler(BadRequestException exception){
        return buildResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<RestErrorMessage> conflictHandler(ConflictException exception){
        return buildResponse(HttpStatus.CONFLICT, exception.getMessage());
    }

    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<RestErrorMessage> internalServerHandler(InternalServerException exception){
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
    }
}
