package com.campestre.clube.backend_application.core.application.statement;

import com.campestre.clube.backend_application.core.adapter.StatementGateway;
import com.campestre.clube.backend_application.core.application.exceptions.NotFoundException;
import com.campestre.clube.backend_application.core.application.statement.command.GetStatementByIdCommand;
import com.campestre.clube.backend_application.core.domain.Statement;

public class GetStatementByIdUseCase {

    private final StatementGateway gateway;

    public GetStatementByIdUseCase(StatementGateway gateway) {
        this.gateway = gateway;
    }

    public Statement execute(GetStatementByIdCommand command) {
        if (gateway.existsById(command.id()))
            throw new NotFoundException("A transação não foi encontrado");
        return gateway.findById(command.id());
    }
}
