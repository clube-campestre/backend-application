package com.campestre.clube.backend_application.infrastructure.web;

import com.campestre.clube.backend_application.core.exceptions.*;
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

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<RestErrorMessage> invalidRequestHandler(InvalidRequestException exception){
        return buildResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<RestErrorMessage> unauthorizedHandler(UnauthorizedException exception){
        return buildResponse(HttpStatus.UNAUTHORIZED, exception.getMessage());
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<RestErrorMessage> forbiddenHandler(ForbiddenException exception){
        return buildResponse(HttpStatus.FORBIDDEN, exception.getMessage());
    }
}
