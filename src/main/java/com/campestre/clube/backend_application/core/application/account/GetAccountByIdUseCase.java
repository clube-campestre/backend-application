package com.campestre.clube.backend_application.core.application.account;

import com.campestre.clube.backend_application.core.adapter.AccountGateway;
import com.campestre.clube.backend_application.core.application.account.command.GetAccountByIdCommand;
import com.campestre.clube.backend_application.core.domain.Account;

import static com.campestre.clube.backend_application.core.exceptions.ExceptionExtensions.NOT_FOUND_ACCOUNT;

public class GetAccountByIdUseCase {

    private final AccountGateway gateway;

    public GetAccountByIdUseCase(AccountGateway gateway) {
        this.gateway = gateway;
    }

    public Account execute(GetAccountByIdCommand command) {
        if (!gateway.existsById(command.id())) throw NOT_FOUND_ACCOUNT;
        return gateway.findById(command.id());
    }
}
