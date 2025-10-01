package com.campestre.clube.backend_application.core.application.tag;

import com.campestre.clube.backend_application.core.adapter.TagGateway;
import com.campestre.clube.backend_application.core.application.tag.command.UpdateTagCommand;
import com.campestre.clube.backend_application.core.domain.Tag;

import static com.campestre.clube.backend_application.core.application.utils.ExceptionExtensions.*;

public class UpdateTagUseCase {

    private final TagGateway gateway;

    public UpdateTagUseCase(TagGateway gateway) {
        this.gateway = gateway;
    }

    public Tag execute(UpdateTagCommand command) {
        if (!gateway.existsById(command.id())) throw NOT_FOUND_TAG;
        if (gateway.existsBySurnameIgnoreCaseIdNot(command.surname(), command.id())) throw CONFLICT_TAG_SAME_SURNAME;
        if (gateway.existsByColorAndIdNot(command.color(), command.id())) throw CONFLICT_TAG_SAME_COLOR;

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
