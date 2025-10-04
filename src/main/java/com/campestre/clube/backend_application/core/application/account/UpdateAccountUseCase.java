package com.campestre.clube.backend_application.core.application.account;

import com.campestre.clube.backend_application.core.adapter.AccountGateway;
import com.campestre.clube.backend_application.core.application.account.command.UpdateAccountCommand;
import com.campestre.clube.backend_application.core.domain.Account;

import static com.campestre.clube.backend_application.core.exceptions.ExceptionExtensions.*;

public class UpdateAccountUseCase {

    private final AccountGateway gateway;

    public UpdateAccountUseCase(AccountGateway gateway) {
        this.gateway = gateway;
    }

    public Account execute(UpdateAccountCommand command) {
        if (gateway.existsByEmailAndIdNot(command.email(), command.id())) throw CONFLICT_ACCOUNT_SAME_EMAIL;

        Account account = gateway.findById(command.id());

        Account newAccount = Account.of(
                command.id(),
                command.email(),
                account.getPassword(),
                command.name(),
                command.access()
        );
        return gateway.save(newAccount);
    }
}
