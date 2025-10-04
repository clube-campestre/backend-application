package com.campestre.clube.backend_application.core.application.statement;

import com.campestre.clube.backend_application.core.adapter.StatementGateway;
import com.campestre.clube.backend_application.core.adapter.TagGateway;
import com.campestre.clube.backend_application.core.application.statement.command.SaveStatementCommand;
import com.campestre.clube.backend_application.core.domain.Statement;
import com.campestre.clube.backend_application.core.domain.Tag;

import static com.campestre.clube.backend_application.core.exceptions.ExceptionExtensions.CONFLICT_STATEMENT_SAME_INFORMATION_AND_PRICE_AND_TRANSACTION_DATE_AND_TAG;
import static com.campestre.clube.backend_application.core.exceptions.ExceptionExtensions.NOT_FOUND_TAG;

public class SaveStatementUseCase {

    private final StatementGateway gateway;
    private final TagGateway tagGateway;

    public SaveStatementUseCase(StatementGateway gateway, TagGateway tagGateway) {
        this.gateway = gateway;
        this.tagGateway = tagGateway;
    }

    public Statement execute(SaveStatementCommand command) {
        if(!tagGateway.existsBySurnameIgnoreCase(command.tagSurname())) throw NOT_FOUND_TAG;

        Tag tag = tagGateway.findBySurnameIgnoreCase(command.tagSurname());

        if (gateway.existsByInformationAndPriceAndTransactionDateAndTag(
                command.information(), command.price(), command.transactionDate(), tag
        )) throw CONFLICT_STATEMENT_SAME_INFORMATION_AND_PRICE_AND_TRANSACTION_DATE_AND_TAG;

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
