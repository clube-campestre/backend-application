package com.campestre.clube.backend_application.core.application.tag;

import com.campestre.clube.backend_application.core.adapter.TagGateway;
import com.campestre.clube.backend_application.core.application.exceptions.NotFoundException;
import com.campestre.clube.backend_application.core.application.tag.command.GetTagByIdCommand;
import com.campestre.clube.backend_application.core.domain.Tag;

public class GetTagByIdUseCase {

    private final TagGateway gateway;

    public GetTagByIdUseCase(TagGateway gateway) {
        this.gateway = gateway;
    }

    public Tag execute(GetTagByIdCommand command) {
        if (gateway.existsById(command.id()))
            throw new NotFoundException("A tag não foi encontrada");
        return gateway.findById(command.id());
    }
}
