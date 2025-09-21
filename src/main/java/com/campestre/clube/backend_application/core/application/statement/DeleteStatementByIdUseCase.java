package com.campestre.clube.backend_application.core.application.statement;

import com.campestre.clube.backend_application.core.adapter.StatementGateway;
import com.campestre.clube.backend_application.core.application.exceptions.NotFoundException;
import com.campestre.clube.backend_application.core.application.statement.command.DeleteStatementByIdCommand;

public class DeleteStatementByIdUseCase {

    private final StatementGateway gateway;

    public DeleteStatementByIdUseCase(StatementGateway gateway) {
        this.gateway = gateway;
    }

    public void execute(DeleteStatementByIdCommand command) {
        if (gateway.existsById(command.id()))
            throw new NotFoundException("A transação não foi encontrada");
        gateway.removeById(command.id());
    }
}
