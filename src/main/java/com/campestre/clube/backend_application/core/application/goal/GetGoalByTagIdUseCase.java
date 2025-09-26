package com.campestre.clube.backend_application.core.application.goal;

import com.campestre.clube.backend_application.core.adapter.StatementGateway;
import com.campestre.clube.backend_application.core.application.goal.command.GetGoalByTagIdCommand;
import com.campestre.clube.backend_application.core.domain.Goal;

import static com.campestre.clube.backend_application.core.application.extensions.ExceptionExtensions.NOT_FOUND_GOAL_BY_TAG;

public class GetGoalByTagIdUseCase {

    private final StatementGateway gateway;

    public GetGoalByTagIdUseCase(StatementGateway gateway) {
        this.gateway = gateway;
    }

    public Goal execute(GetGoalByTagIdCommand command) {
        if (!gateway.existsByTagId(command.tagId())) throw NOT_FOUND_GOAL_BY_TAG;
        return gateway.findGoalByTagId(command.tagId());
    }
}
