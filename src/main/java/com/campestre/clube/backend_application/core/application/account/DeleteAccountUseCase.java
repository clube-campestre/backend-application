package com.campestre.clube.backend_application.core.application.account;

import com.campestre.clube.backend_application.core.adapter.AccountGateway;
import com.campestre.clube.backend_application.core.application.account.command.DeleteAccountCommand;

import static com.campestre.clube.backend_application.core.exceptions.ExceptionExtensions.NOT_FOUND_ACCOUNT;

public class DeleteAccountUseCase {

    private final AccountGateway gateway;

    public DeleteAccountUseCase(AccountGateway gateway) {
        this.gateway = gateway;
    }

    public void execute(DeleteAccountCommand command) {
        if (!gateway.existsById(command.id())) throw NOT_FOUND_ACCOUNT;
        gateway.removeById(command.id());
    }
}
