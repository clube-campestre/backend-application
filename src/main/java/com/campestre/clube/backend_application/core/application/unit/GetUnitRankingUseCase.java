package com.campestre.clube.backend_application.core.application.unit;

import com.campestre.clube.backend_application.core.adapter.UnitGateway;
import com.campestre.clube.backend_application.core.domain.Unit;

import java.util.List;

public class GetUnitRankingUseCase {

    private final UnitGateway gateway;

    public GetUnitRankingUseCase(UnitGateway gateway) {
        this.gateway = gateway;
    }

    public List<Unit> execute() {
        return gateway.findByHasRankingOrderByScoreDesc(true);
    }
}
