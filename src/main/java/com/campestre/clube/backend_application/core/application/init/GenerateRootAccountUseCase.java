package com.campestre.clube.backend_application.core.application.init;

import com.campestre.clube.backend_application.core.adapter.AccountGateway;
import com.campestre.clube.backend_application.core.domain.Account;
import com.campestre.clube.backend_application.core.domain.enums.AccessTypeEnum;
import org.springframework.stereotype.Service;

@Service
public class GenerateRootAccountUseCase {

    private final AccountGateway accountGateway;

    public GenerateRootAccountUseCase(AccountGateway accountGateway) {
        this.accountGateway = accountGateway;
    }

    public void execute() {
        if (accountGateway.findAll().isEmpty()) accountGateway.save(
                Account.of("root@email.com", "1234", "Root", AccessTypeEnum.DIRETOR)
        );
    }
}

