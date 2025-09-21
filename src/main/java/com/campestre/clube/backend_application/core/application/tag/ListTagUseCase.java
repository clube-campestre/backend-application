package com.campestre.clube.backend_application.core.application.tag;

import com.campestre.clube.backend_application.core.adapter.TagGateway;
import com.campestre.clube.backend_application.core.domain.Tag;

import java.util.List;

public class ListTagUseCase {

    private final TagGateway gateway;

    public ListTagUseCase(TagGateway gateway) {
        this.gateway = gateway;
    }

    public List<Tag> execute() {
        return gateway.findAll();
    }
}
