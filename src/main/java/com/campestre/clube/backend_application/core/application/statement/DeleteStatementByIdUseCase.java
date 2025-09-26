package com.campestre.clube.backend_application.core.application.statement;

import com.campestre.clube.backend_application.core.adapter.StatementGateway;
import com.campestre.clube.backend_application.core.application.statement.command.DeleteStatementByIdCommand;

import static com.campestre.clube.backend_application.core.application.utils.ExceptionExtensions.NOT_FOUND_STATEMENT;

public class DeleteStatementByIdUseCase {

    private final StatementGateway gateway;

    public DeleteStatementByIdUseCase(StatementGateway gateway) {
        this.gateway = gateway;
    }

    public void execute(DeleteStatementByIdCommand command) {
        if (!gateway.existsById(command.id())) throw NOT_FOUND_STATEMENT;
        gateway.removeById(command.id());
    }
}
