
package com.campestre.clube.backend_application.core.application.statement.command;

import com.campestre.clube.backend_application.core.application.statement.valueobject.Filter;
import com.campestre.clube.backend_application.core.application.valueobject.Pagination;

public record ListStatementByFilterAndPaginationCommand(Filter filter, Pagination pagination){}