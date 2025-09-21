package com.campestre.clube.backend_application.core.application.statement;

import com.campestre.clube.backend_application.core.adapter.StatementGateway;
import com.campestre.clube.backend_application.core.application.statement.command.ListStatementByFilterAndPaginationCommand;
import com.campestre.clube.backend_application.core.domain.StatementInformations;

public class ListStatementByFilterAndPaginationUseCase {

    private final StatementGateway gateway;

    public ListStatementByFilterAndPaginationUseCase(StatementGateway gateway) {
        this.gateway = gateway;
    }

    public StatementInformations execute(ListStatementByFilterAndPaginationCommand command) {
        return gateway.findStatementInformationsByFilterAndPagination(command.filter(), command.pagination());
    }
}
