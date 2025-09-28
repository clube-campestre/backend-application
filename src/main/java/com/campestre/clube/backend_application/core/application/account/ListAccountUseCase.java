package com.campestre.clube.backend_application.core.application.account;

import com.campestre.clube.backend_application.core.adapter.AccountGateway;
import com.campestre.clube.backend_application.core.domain.Account;

import java.util.List;

public class ListAccountUseCase {

    private final AccountGateway gateway;

    public ListAccountUseCase(AccountGateway gateway) {
        this.gateway = gateway;
    }

    public List<Account> execute() {
        return gateway.findAll();
    }
}
