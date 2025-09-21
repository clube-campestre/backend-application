package com.campestre.clube.backend_application.core.application.goal;

import com.campestre.clube.backend_application.core.adapter.StatementGateway;
import com.campestre.clube.backend_application.core.application.exceptions.NotFoundException;
import com.campestre.clube.backend_application.core.application.goal.command.GetGoalByTagIdCommand;
import com.campestre.clube.backend_application.core.domain.Goal;

public class GetGoalByTagIdUseCase {

    private final StatementGateway gateway;

    public GetGoalByTagIdUseCase(StatementGateway gateway) {
        this.gateway = gateway;
    }

    public Goal execute(GetGoalByTagIdCommand command) {
        if (gateway.existsByTagId(command.tagId()))
            throw new NotFoundException("A tag da meta não foi encontrado");
        return gateway.findGoalByTagId(command.tagId());
    }
}
