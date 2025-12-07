package com.campestre.clube.backend_application.core.application.unit;

import com.campestre.clube.backend_application.core.adapter.UnitGateway;
import com.campestre.clube.backend_application.core.domain.Unit;

public class ResetUnitScoresUseCase {

    private final UnitGateway gateway;

    public ResetUnitScoresUseCase(UnitGateway gateway) {
        this.gateway = gateway;
    }

    public Boolean execute() {
        gateway.findByHasRankingOrderByScoreDesc(true).forEach(unit ->
                gateway.save(Unit.of(unit.getId(), unit.getSurname()))
        );
        return gateway.findByScoreNot(0).isEmpty();
    }
}
