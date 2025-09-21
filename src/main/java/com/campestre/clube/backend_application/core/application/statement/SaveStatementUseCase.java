package com.campestre.clube.backend_application.core.application.statement;

import com.campestre.clube.backend_application.core.adapter.StatementGateway;
import com.campestre.clube.backend_application.core.adapter.TagGateway;
import com.campestre.clube.backend_application.core.application.exceptions.ConflictException;
import com.campestre.clube.backend_application.core.application.exceptions.NotFoundException;
import com.campestre.clube.backend_application.core.application.statement.command.SaveStatementCommand;
import com.campestre.clube.backend_application.core.domain.Statement;
import com.campestre.clube.backend_application.core.domain.Tag;

public class SaveStatementUseCase {

    private final StatementGateway gateway;
    private final TagGateway tagGateway;

    public SaveStatementUseCase(StatementGateway gateway, TagGateway tagGateway) {
        this.gateway = gateway;
        this.tagGateway = tagGateway;
    }

    public Statement execute(SaveStatementCommand command) {
        if(!tagGateway.existsBySurnameIgnoreCase(command.tagSurname()))
            throw new NotFoundException("A tag da transação não foi encontrada");

        Tag tag = tagGateway.findBySurnameIgnoreCase(command.tagSurname());

        if (gateway.existsByInformationAndPriceAndTransactionDateAndTag(
                command.information(), command.price(), command.transactionDate(), tag
        )) throw new ConflictException("Já existe um lançamento com as mesmas informações, valor, data e tag.");

        Statement statement = Statement.of(
                command.information(),
                command.price(),
                command.transactionDate(),
                command.transactionType(),
                tag
        );
        return gateway.save(statement);
    }
}
