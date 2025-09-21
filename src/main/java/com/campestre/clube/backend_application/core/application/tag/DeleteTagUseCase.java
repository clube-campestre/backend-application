package com.campestre.clube.backend_application.core.application.tag;

import com.campestre.clube.backend_application.core.adapter.TagGateway;
import com.campestre.clube.backend_application.core.application.exceptions.NotFoundException;
import com.campestre.clube.backend_application.core.application.tag.command.DeleteTagCommand;

public class DeleteTagUseCase {

    private final TagGateway gateway;

    public DeleteTagUseCase(TagGateway gateway) {
        this.gateway = gateway;
    }

    public void execute(DeleteTagCommand command) {
        if (gateway.existsById(command.id()))
            throw new NotFoundException("A tag não foi encontrada");
        gateway.removeById(command.id());
    }
}
