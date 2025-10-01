package com.campestre.clube.backend_application.core.application.goal;

import com.campestre.clube.backend_application.core.adapter.StatementGateway;
import com.campestre.clube.backend_application.core.adapter.TagGateway;
import com.campestre.clube.backend_application.core.application.goal.command.GetGoalByTagIdCommand;
import com.campestre.clube.backend_application.core.domain.Goal;

import static com.campestre.clube.backend_application.core.application.utils.ExceptionExtensions.NOT_FOUND_GOAL_BY_TAG;

public class GetGoalByTagIdUseCase {

    private final StatementGateway statementGateway;
    private final TagGateway tagGateway;

    public GetGoalByTagIdUseCase(StatementGateway statementGateway, TagGateway tagGateway) {
        this.statementGateway = statementGateway;
        this.tagGateway = tagGateway;
    }

    public Goal execute(GetGoalByTagIdCommand command) {
        if (!tagGateway.existsById(command.tagId())) throw NOT_FOUND_GOAL_BY_TAG;
        return statementGateway.findGoalByTagId(command.tagId());
    }
}
