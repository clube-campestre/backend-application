package com.campestre.clube.backend_application.infra;

import com.campestre.clube.backend_application.exceptions.EmailConfictException;
import com.campestre.clube.backend_application.exceptions.LoginBadRequestException;
import com.campestre.clube.backend_application.exceptions.UserNotFoundException;
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
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RestErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage()));
    }

    @ExceptionHandler(LoginBadRequestException.class)
    private ResponseEntity<RestErrorMessage> loginBadRequestHandler(LoginBadRequestException exception){
        return badRequest(exception.getMessage());
    }

    @ExceptionHandler(EmailConfictException.class)
    private ResponseEntity<RestErrorMessage> emailConflictHandler(EmailConfictException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new RestErrorMessage(HttpStatus.CONFLICT, exception.getMessage()));
    }

    @ExceptionHandler(BadRequestException.class)
    private ResponseEntity<RestErrorMessage> badRequestHandler(BadRequestException exception){
        return badRequest(exception.getMessage());
    }

    private ResponseEntity<RestErrorMessage> badRequest(String exceptionMessage) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RestErrorMessage(HttpStatus.BAD_REQUEST, exceptionMessage));
    }
}
