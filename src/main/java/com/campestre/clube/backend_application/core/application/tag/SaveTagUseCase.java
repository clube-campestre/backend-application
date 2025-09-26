package com.campestre.clube.backend_application.core.application.tag;

import com.campestre.clube.backend_application.core.adapter.TagGateway;
import com.campestre.clube.backend_application.core.application.tag.command.SaveTagCommand;
import com.campestre.clube.backend_application.core.domain.Tag;

import static com.campestre.clube.backend_application.core.application.utils.ExceptionExtensions.CONFLICT_TAG_SAME_SURNAME_OR_COLOR;

public class SaveTagUseCase {

    private final TagGateway gateway;

    public SaveTagUseCase(TagGateway gateway) {
        this.gateway = gateway;
    }

    public Tag execute(SaveTagCommand command) {
        if (gateway.existsBySurnameIgnoreCaseOrColorContains(command.surname(), command.color()))
            throw CONFLICT_TAG_SAME_SURNAME_OR_COLOR;

        Tag tag = Tag.of(
                command.surname(),
                command.color(),
                command.goal(),
                command.privateGoal()
        );

        return gateway.save(tag);
    }
}
