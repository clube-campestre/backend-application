package com.campestre.clube.backend_application.core.application.resetpassword;

import com.campestre.clube.backend_application.core.adapter.ResetPasswordGateway;
import com.campestre.clube.backend_application.core.application.resetpassword.command.ValidateResetPasswordCodeCommand;
import com.campestre.clube.backend_application.core.domain.ResetPassword;

import java.time.Instant;
import java.time.ZoneId;

import static com.campestre.clube.backend_application.core.exceptions.ExceptionExtensions.*;

public class ValidateResetPasswordCodeUseCase {
    private final ResetPasswordGateway gateway;

    public ValidateResetPasswordCodeUseCase(ResetPasswordGateway gateway) {
        this.gateway = gateway;
    }

    public Boolean execute(ValidateResetPasswordCodeCommand command) {
        if(!gateway.existsByAccountEmailAndCodeAndNotUsed(command.email(), command.code()))
            throw INVALID_CODE_RESET_PASSWORD;

        ResetPassword resetPassword = gateway.findByAccountEmailAndCodeAndNotUsed(command.email(), command.code());

        if (resetPassword.getExpiration().atZone(ZoneId.systemDefault()).toInstant().isBefore(Instant.now()))
            throw INVALID_CODE_EXPIRED_RESET_PASSWORD;

        return true;
    }
}
