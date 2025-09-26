package com.campestre.clube.backend_application.core.application.tag;

import com.campestre.clube.backend_application.core.adapter.TagGateway;
import com.campestre.clube.backend_application.core.application.tag.command.GetTagByIdCommand;
import com.campestre.clube.backend_application.core.domain.Tag;

import static com.campestre.clube.backend_application.core.application.utils.ExceptionExtensions.NOT_FOUND_TAG;

public class GetTagByIdUseCase {

    private final TagGateway gateway;

    public GetTagByIdUseCase(TagGateway gateway) {
        this.gateway = gateway;
    }

    public Tag execute(GetTagByIdCommand command) {
        if (!gateway.existsById(command.id())) throw NOT_FOUND_TAG;
        return gateway.findById(command.id());
    }
}
