package com.campestre.clube.backend_application.core.application.statement;

import com.campestre.clube.backend_application.core.adapter.StatementGateway;
import com.campestre.clube.backend_application.core.application.statement.command.GetStatementByIdCommand;
import com.campestre.clube.backend_application.core.domain.Statement;

import static com.campestre.clube.backend_application.core.application.utils.ExceptionExtensions.NOT_FOUND_STATEMENT;

public class GetStatementByIdUseCase {

    private final StatementGateway gateway;

    public GetStatementByIdUseCase(StatementGateway gateway) {
        this.gateway = gateway;
    }

    public Statement execute(GetStatementByIdCommand command) {
        if (!gateway.existsById(command.id())) throw NOT_FOUND_STATEMENT;
        return gateway.findById(command.id());
    }
}
