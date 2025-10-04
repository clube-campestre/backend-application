package com.campestre.clube.backend_application.infrastructure.config;

import com.campestre.clube.backend_application.core.application.init.GenerateGenericTagUseCase;
import com.campestre.clube.backend_application.core.application.init.GenerateRootAccountUseCase;
import com.campestre.clube.backend_application.core.application.init.GenerateUnitsUseCase;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class InitConfiguration {
    private final GenerateGenericTagUseCase generateGenericTagUseCase;
    private final GenerateRootAccountUseCase generateRootAccountUseCase;
    private final GenerateUnitsUseCase generateUnitsUseCase;

    public InitConfiguration(
            GenerateGenericTagUseCase generateGenericTagUseCase,
            GenerateRootAccountUseCase generateRootAccountUseCase,
            GenerateUnitsUseCase generateUnitsUseCase
    ) {
        this.generateGenericTagUseCase = generateGenericTagUseCase;
        this.generateRootAccountUseCase = generateRootAccountUseCase;
        this.generateUnitsUseCase = generateUnitsUseCase;
    }

    @PostConstruct
    public void executeInitConfiguration() {
        generateGenericTagUseCase.execute();
        generateRootAccountUseCase.execute();
        generateUnitsUseCase.execute();
    }
}
