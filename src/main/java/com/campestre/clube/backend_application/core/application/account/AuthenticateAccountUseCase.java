package com.campestre.clube.backend_application.core.application.account;

import com.campestre.clube.backend_application.core.adapter.AccountGateway;
import com.campestre.clube.backend_application.core.adapter.PasswordHasherGateway;
import com.campestre.clube.backend_application.core.adapter.TokenGeneratorGateway;
import com.campestre.clube.backend_application.core.application.account.command.AuthenticateAccountCommand;
import com.campestre.clube.backend_application.core.domain.Account;
import com.campestre.clube.backend_application.core.domain.LoginAccount;

import static com.campestre.clube.backend_application.core.exceptions.ExceptionExtensions.*;

public class AuthenticateAccountUseCase {

    private final AccountGateway gateway;
    private final PasswordHasherGateway passwordHasherGateway;
    private final TokenGeneratorGateway tokenGeneratorGateway;

    public AuthenticateAccountUseCase(
            AccountGateway gateway, PasswordHasherGateway passwordHasherGateway,
            TokenGeneratorGateway tokenGeneratorGateway
    ) {
        this.gateway = gateway;
        this.passwordHasherGateway = passwordHasherGateway;
        this.tokenGeneratorGateway = tokenGeneratorGateway;
    }

    public LoginAccount execute(AuthenticateAccountCommand command) {
        if (!gateway.existsByEmail(command.email())) throw NOT_FOUND_ACCOUNT;

        Account account = gateway.findByEmail(command.email());

        if (!passwordHasherGateway.matches(command.password(), account.getPassword())) throw BAD_REQUEST_ACCOUNT;

        String token = tokenGeneratorGateway.generate(account);

        return new LoginAccount(
                account.getId(),
                account.getEmail().getValue(),
                token,
                account.getName(),
                account.getAccess().name()
        );
    }
}
