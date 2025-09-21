package com.campestre.clube.backend_application.core.application.statement;

import com.campestre.clube.backend_application.core.adapter.StatementGateway;
import com.campestre.clube.backend_application.core.application.exceptions.NotFoundException;
import com.campestre.clube.backend_application.core.application.statement.command.DeleteStatementByTagSurnameCommand;

public class DeleteStatementByTagSurnameUseCase {

    private final StatementGateway gateway;

    public DeleteStatementByTagSurnameUseCase(StatementGateway gateway) {
        this.gateway = gateway;
    }

    public void execute(DeleteStatementByTagSurnameCommand command) {
        if (gateway.existsByTagSurname(command.tagSurname()))
            throw new NotFoundException("A tag da transação não foi encontrado");
        gateway.removeByTagSurname(command.tagSurname());
    }
}
