package com.campestre.clube.backend_application.core.application.init;

import com.campestre.clube.backend_application.core.adapter.TagGateway;
import org.springframework.stereotype.Service;
import com.campestre.clube.backend_application.core.domain.Tag;

@Service
public class GenerateGenericTagUseCase {

    private final TagGateway tagGateway;

    public GenerateGenericTagUseCase(TagGateway tagGateway) {
        this.tagGateway = tagGateway;
    }

    public void execute() {
        if (!tagGateway.existsBySurnameIgnoreCase("OUTROS"))
            tagGateway.save(Tag.of("OUTROS", "FFFFFF", null, null));
    }
}

