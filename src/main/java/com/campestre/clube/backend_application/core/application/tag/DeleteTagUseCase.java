package com.campestre.clube.backend_application.core.application.tag;

import com.campestre.clube.backend_application.core.adapter.StatementGateway;
import com.campestre.clube.backend_application.core.adapter.TagGateway;
import com.campestre.clube.backend_application.core.application.tag.command.DeleteTagCommand;
import com.campestre.clube.backend_application.core.domain.Tag;

import static com.campestre.clube.backend_application.core.exceptions.ExceptionExtensions.NOT_FOUND_TAG;

public class DeleteTagUseCase {

    private final TagGateway gateway;
    private final StatementGateway statementGateway;

    public DeleteTagUseCase(TagGateway gateway, StatementGateway statementGateway) {
        this.gateway = gateway;
        this.statementGateway = statementGateway;
    }

    public void execute(DeleteTagCommand command) {
        if (!gateway.existsById(command.id())) throw NOT_FOUND_TAG;

        Tag genericTag =gateway.findBySurnameIgnoreCase("OUTROS");
        statementGateway.findByTagId(command.id()).stream().forEach(statement -> {
            statement.setTag(genericTag);
            statementGateway.save(statement);
        });
        gateway.removeById(command.id());
    }
}
