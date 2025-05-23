package com.campestre.clube.backend_application.exceptions.handlers;

import com.campestre.clube.backend_application.exceptions.BadRequestException;
import com.campestre.clube.backend_application.exceptions.ConflictException;
import com.campestre.clube.backend_application.exceptions.InternalServerException;
import com.campestre.clube.backend_application.exceptions.NotFoundException;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Hidden
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<RestErrorMessage> notFoundHandler(NotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new RestErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage())
        );
    }

    @ExceptionHandler(BadRequestException.class)
    private ResponseEntity<RestErrorMessage> badRequestHandler(BadRequestException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new RestErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage())
        );
    }

    @ExceptionHandler(ConflictException.class)
    private ResponseEntity<RestErrorMessage> conflictHandler(ConflictException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new RestErrorMessage(HttpStatus.CONFLICT, exception.getMessage())
        );
    }

    @ExceptionHandler(InternalServerException.class)
    private ResponseEntity<RestErrorMessage> internalServerHandler(InternalServerException exception){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new RestErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage())
        );
    }
}
