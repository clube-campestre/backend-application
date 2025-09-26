package com.campestre.clube.backend_application.core.application.unit;

import com.campestre.clube.backend_application.core.adapter.UnitGateway;
import com.campestre.clube.backend_application.core.application.unit.command.ChangeUnitScoreCommand;
import com.campestre.clube.backend_application.core.domain.Unit;
import com.campestre.clube.backend_application.core.domain.enums.UnitEnum;

import static com.campestre.clube.backend_application.core.application.utils.ExceptionExtensions.*;

public class ChangeUnitScoreUseCase {

    private final UnitGateway gateway;

    public ChangeUnitScoreUseCase(UnitGateway gateway) {
        this.gateway = gateway;
    }

    public Unit execute(ChangeUnitScoreCommand command) {
        UnitEnum.fromString(command.unitName());

        if (command.score() == null) throw BAD_REQUEST_UNIT_SCORE_MUST_NOT_BE_NULL;
        if (!gateway.existsBySurnameIgnoreCase(command.unitName())) throw NOT_FOUND_UNIT;

        Unit unit = gateway.findBySurnameIgnoreCase(command.unitName());
        Unit newUnit = Unit.of(
                unit.getId(),
                unit.getSurname(),
                (command.isSum() ? (unit.getScore() + command.score()) : (unit.getScore() - command.score()))
        );
        return gateway.save(newUnit);
    }
}
