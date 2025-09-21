package com.campestre.clube.backend_application.core.application.tag;

import com.campestre.clube.backend_application.core.adapter.TagGateway;
import com.campestre.clube.backend_application.core.application.exceptions.ConflictException;
import com.campestre.clube.backend_application.core.application.tag.command.SaveTagCommand;
import com.campestre.clube.backend_application.core.domain.Tag;

public class SaveTagUseCase {

    private final TagGateway gateway;

    public SaveTagUseCase(TagGateway gateway) {
        this.gateway = gateway;
    }

    public Tag execute(SaveTagCommand command) {
        if (gateway.existsBySurnameIgnoreCaseOrColorContains(command.surname(), command.color()))
            throw new ConflictException("Já existe uma tag com este nome ou cor");

        Tag tag = Tag.of(
                command.surname(),
                command.color(),
                command.goal(),
                command.privateGoal()
        );

        return gateway.save(tag);
    }
}
