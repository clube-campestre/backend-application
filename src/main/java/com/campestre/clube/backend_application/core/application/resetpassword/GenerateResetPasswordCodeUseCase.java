package com.campestre.clube.backend_application.core.application.resetpassword;

import com.campestre.clube.backend_application.core.adapter.AccountGateway;
import com.campestre.clube.backend_application.core.adapter.NotificationGateway;
import com.campestre.clube.backend_application.core.adapter.ResetPasswordGateway;
import com.campestre.clube.backend_application.core.application.resetpassword.command.GenerateResetPasswordCodeCommand;
import com.campestre.clube.backend_application.core.domain.Account;
import com.campestre.clube.backend_application.core.domain.ResetPassword;

import java.time.LocalDateTime;
import java.util.Random;

import static com.campestre.clube.backend_application.core.exceptions.ExceptionExtensions.*;

public class GenerateResetPasswordCodeUseCase {
    private final ResetPasswordGateway gateway;
    private final NotificationGateway notificationGateway;
    private final AccountGateway accountGateway;

    public GenerateResetPasswordCodeUseCase(
            ResetPasswordGateway gateway,
            NotificationGateway notificationGateway,
            AccountGateway accountGateway
    ) {
        this.gateway = gateway;
        this.notificationGateway = notificationGateway;
        this.accountGateway = accountGateway;
    }

    public void execute(GenerateResetPasswordCodeCommand command) {
        if(!accountGateway.existsByEmail(command.email())) throw NOT_FOUND_MEMBER_DATA_BY_EMAIL;

        Account account = accountGateway.findByEmail(command.email());

        String code = String.format("%06d", new Random().nextInt(999999));
        ResetPassword resetCode = ResetPassword.of(
                account,
                code,
                LocalDateTime.now().plusMinutes(10),
                false
        );

        gateway.save(resetCode);

        notificationGateway.sendEmail(command.email(), "Código de recuperação de senha",
                "Seu código é: " + code + "\nEle expira em 10 minutos.");
    }
}
