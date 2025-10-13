package com.campestre.clube.backend_application.core.application.resetpassword;

import com.campestre.clube.backend_application.core.adapter.AccountGateway;
import com.campestre.clube.backend_application.core.adapter.PasswordHasherGateway;
import com.campestre.clube.backend_application.core.adapter.ResetPasswordGateway;
import com.campestre.clube.backend_application.core.application.resetpassword.command.ResetPasswordCommand;
import com.campestre.clube.backend_application.core.domain.Account;
import com.campestre.clube.backend_application.core.domain.ResetPassword;

import java.time.LocalDateTime;

import static com.campestre.clube.backend_application.core.exceptions.ExceptionExtensions.*;

public class ResetPasswordUseCase {
    private final ResetPasswordGateway gateway;
    private final PasswordHasherGateway passwordHasherGateway;
    private final AccountGateway accountGateway;

    public ResetPasswordUseCase(
            ResetPasswordGateway gateway,
            PasswordHasherGateway passwordHasherGateway,
            AccountGateway accountGateway
    ) {
        this.gateway = gateway;
        this.passwordHasherGateway = passwordHasherGateway;
        this.accountGateway = accountGateway;
    }

    public void execute(ResetPasswordCommand command) {
        if(!gateway.existsByAccountEmailAndCodeAndNotUsed(command.email(), command.code()))
            throw INVALID_CODE_RESET_PASSWORD;

        ResetPassword resetPassword = gateway.findByAccountEmailAndCodeAndNotUsed(command.email(), command.code());

        if (resetPassword.getExpiration().isBefore(LocalDateTime.now())) throw INVALID_CODE_EXPIRED_RESET_PASSWORD;

        Account account = Account.of(
                resetPassword.getAccount().getId(),
                resetPassword.getAccount().getEmail().getValue(),
                passwordHasherGateway.hash(command.newPassword()),
                resetPassword.getAccount().getName(),
                resetPassword.getAccount().getAccess()
        );
        accountGateway.save(account);

        resetPassword.setUsed(true);
        gateway.save(resetPassword);
    }
}
