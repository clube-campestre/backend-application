package com.campestre.clube.backend_application.core.application.unit;

import com.campestre.clube.backend_application.core.adapter.UnitGateway;
import com.campestre.clube.backend_application.core.application.unit.command.UpdateUnitScoreCommand;
import com.campestre.clube.backend_application.core.domain.Unit;
import com.campestre.clube.backend_application.core.domain.enums.UnitEnum;

import static com.campestre.clube.backend_application.core.application.utils.ExceptionExtensions.BAD_REQUEST_UNIT_SCORE_MUST_NOT_BE_NULL;
import static com.campestre.clube.backend_application.core.application.utils.ExceptionExtensions.NOT_FOUND_UNIT;

public class UpdateUnitScoreUseCase {

    private final UnitGateway gateway;

    public UpdateUnitScoreUseCase(UnitGateway gateway) {
        this.gateway = gateway;
    }

    public Unit execute(UpdateUnitScoreCommand command) {
        UnitEnum.fromString(command.unitName());

        if (command.newScore() == null) throw BAD_REQUEST_UNIT_SCORE_MUST_NOT_BE_NULL;
        if (!gateway.existsBySurnameIgnoreCase(command.unitName())) throw NOT_FOUND_UNIT;

        Unit unit = gateway.findBySurnameIgnoreCase(command.unitName());
        Unit newUnit = Unit.of(unit.getId(), unit.getSurname(), command.newScore());
        return gateway.save(newUnit);
    }
}
