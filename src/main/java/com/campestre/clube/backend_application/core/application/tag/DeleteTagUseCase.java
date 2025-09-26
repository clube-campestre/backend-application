package com.campestre.clube.backend_application.core.application.tag;

import com.campestre.clube.backend_application.core.adapter.TagGateway;
import com.campestre.clube.backend_application.core.application.tag.command.DeleteTagCommand;

import static com.campestre.clube.backend_application.core.application.utils.ExceptionExtensions.NOT_FOUND_TAG;

public class DeleteTagUseCase {

    private final TagGateway gateway;

    public DeleteTagUseCase(TagGateway gateway) {
        this.gateway = gateway;
    }

    public void execute(DeleteTagCommand command) {
        if (gateway.existsById(command.id())) throw NOT_FOUND_TAG;
        gateway.removeById(command.id());
    }
}
