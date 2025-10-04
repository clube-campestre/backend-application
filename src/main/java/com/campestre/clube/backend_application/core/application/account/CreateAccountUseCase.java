package com.campestre.clube.backend_application.core.application.account;

import com.campestre.clube.backend_application.core.adapter.AccountGateway;
import com.campestre.clube.backend_application.core.adapter.PasswordHasherGateway;
import com.campestre.clube.backend_application.core.application.account.command.CreateAccountCommand;
import com.campestre.clube.backend_application.core.domain.Account;

import static com.campestre.clube.backend_application.core.exceptions.ExceptionExtensions.CONFLICT_ACCOUNT_SAME_EMAIL;

public class CreateAccountUseCase {

    private final AccountGateway gateway;
    private final PasswordHasherGateway passwordHasherGateway;

    public CreateAccountUseCase(AccountGateway gateway, PasswordHasherGateway passwordHasherGateway) {
        this.gateway = gateway;
        this.passwordHasherGateway = passwordHasherGateway;
    }

    public Account execute(CreateAccountCommand command) {
        if (gateway.existsByEmail(command.email())) throw CONFLICT_ACCOUNT_SAME_EMAIL;

        Account account = Account.of(
                command.email(),
                passwordHasherGateway.hash(command.password()),
                command.name(),
                command.access()
        );
        return gateway.save(account);
    }
}
