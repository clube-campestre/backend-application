package com.campestre.clube.backend_application.core.application.statement;

import com.campestre.clube.backend_application.core.adapter.StatementGateway;
import com.campestre.clube.backend_application.core.adapter.TagGateway;
import com.campestre.clube.backend_application.core.application.statement.command.UpdateStatementCommand;
import com.campestre.clube.backend_application.core.domain.Statement;
import com.campestre.clube.backend_application.core.domain.Tag;

import static com.campestre.clube.backend_application.core.application.utils.ExceptionExtensions.NOT_FOUND_STATEMENT;
import static com.campestre.clube.backend_application.core.application.utils.ExceptionExtensions.NOT_FOUND_TAG;

public class UpdateStatementUseCase {

    private final StatementGateway gateway;
    private final TagGateway tagGateway;

    public UpdateStatementUseCase(StatementGateway gateway, TagGateway tagGateway) {
        this.gateway = gateway;
        this.tagGateway = tagGateway;
    }

    public Statement execute(UpdateStatementCommand command) {
        if (!gateway.existsById(command.id())) throw NOT_FOUND_STATEMENT;
        if(!tagGateway.existsBySurnameIgnoreCase(command.tagSurname())) throw NOT_FOUND_TAG;

        Tag tag = tagGateway.findBySurnameIgnoreCase(command.tagSurname());
        Statement statement = Statement.of(
                command.id(),
                command.information(),
                command.price(),
                command.transactionDate(),
                command.transactionType(),
                tag
        );

        return gateway.save(statement);
    }
}
