package com.campestre.clube.backend_application.core.application.init;

import com.campestre.clube.backend_application.core.adapter.*;
import com.campestre.clube.backend_application.core.domain.Unit;
import com.campestre.clube.backend_application.core.domain.enums.UnitEnum;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class GenerateUnitsUseCase {

    private final UnitGateway unitGateway;

    public GenerateUnitsUseCase(UnitGateway unitGateway) {
        this.unitGateway = unitGateway;
    }

    public void execute() {
        Arrays.stream(UnitEnum.values()).forEach(unit ->
                saveUnitIfNotExist(unit.getId(), unit.name(), unit.getHasRanking())
        );
    }

    private void saveUnitIfNotExist(Long id, String name, Boolean hasRanking) {
        if (!unitGateway.existsBySurnameIgnoreCase(name)) unitGateway.save(Unit.of(id, name, 0, hasRanking));
    }
}

