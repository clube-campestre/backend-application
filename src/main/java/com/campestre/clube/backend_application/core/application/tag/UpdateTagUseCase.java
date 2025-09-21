package com.campestre.clube.backend_application.core.application.tag;

import com.campestre.clube.backend_application.core.adapter.TagGateway;
import com.campestre.clube.backend_application.core.application.exceptions.ConflictException;
import com.campestre.clube.backend_application.core.application.exceptions.NotFoundException;
import com.campestre.clube.backend_application.core.application.tag.command.UpdateTagCommand;
import com.campestre.clube.backend_application.core.domain.Tag;

public class UpdateTagUseCase {

    private final TagGateway gateway;

    public UpdateTagUseCase(TagGateway gateway) {
        this.gateway = gateway;
    }

    public Tag execute(UpdateTagCommand command) {
        if (gateway.existsById(command.id()))
            throw new NotFoundException("A tag não foi encontrada");

        if (gateway.existsBySurnameIgnoreCaseOrColorContainsAndIdNot(
                command.surname(), command.color(), command.id()
        ))
            throw new ConflictException("Já existe uma tag com este nome ou cor");

        Tag tag = Tag.of(
                command.id(),
                command.surname(),
                command.color(),
                command.goal(),
                command.privateGoal()
        );

        return gateway.save(tag);
    }
}
