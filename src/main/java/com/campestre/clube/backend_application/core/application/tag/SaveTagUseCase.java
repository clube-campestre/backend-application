package com.campestre.clube.backend_application.core.application.tag;

import com.campestre.clube.backend_application.core.adapter.TagGateway;
import com.campestre.clube.backend_application.core.application.tag.command.SaveTagCommand;
import com.campestre.clube.backend_application.core.domain.Tag;

import java.math.BigDecimal;

import static com.campestre.clube.backend_application.core.application.utils.ExceptionExtensions.*;

public class SaveTagUseCase {

    private final TagGateway gateway;

    public SaveTagUseCase(TagGateway gateway) {
        this.gateway = gateway;
    }

    public Tag execute(SaveTagCommand command) {
        if (gateway.existsBySurnameIgnoreCaseOrColorContains(command.surname(), command.color()))
            throw CONFLICT_TAG_SAME_SURNAME_OR_COLOR;
        if (command.goal() != null && command.goal() == BigDecimal.ZERO) throw INVALID_REQUEST_TAG_WITH_ZERO_GOAL;

        Tag tag = Tag.of(
                command.surname(),
                command.color(),
                command.goal(),
                command.privateGoal()
        );

        return gateway.save(tag);
    }
}
