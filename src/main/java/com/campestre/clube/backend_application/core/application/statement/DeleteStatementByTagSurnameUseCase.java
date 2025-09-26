package com.campestre.clube.backend_application.core.application.statement;

import com.campestre.clube.backend_application.core.adapter.StatementGateway;
import com.campestre.clube.backend_application.core.application.statement.command.DeleteStatementByTagSurnameCommand;

import static com.campestre.clube.backend_application.core.application.extensions.ExceptionExtensions.NOT_FOUND_TAG;

public class DeleteStatementByTagSurnameUseCase {

    private final StatementGateway gateway;

    public DeleteStatementByTagSurnameUseCase(StatementGateway gateway) {
        this.gateway = gateway;
    }

    public void execute(DeleteStatementByTagSurnameCommand command) {
        if (!gateway.existsByTagSurname(command.tagSurname())) throw NOT_FOUND_TAG;
        gateway.removeByTagSurname(command.tagSurname());
    }
}
