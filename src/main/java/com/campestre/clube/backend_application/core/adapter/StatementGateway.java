package com.campestre.clube.backend_application.core.adapter;

import com.campestre.clube.backend_application.core.application.statement.valueobject.Filter;
import com.campestre.clube.backend_application.core.application.valueobject.Pagination;
import com.campestre.clube.backend_application.core.domain.Goal;
import com.campestre.clube.backend_application.core.domain.Statement;
import com.campestre.clube.backend_application.core.domain.StatementInformations;
import com.campestre.clube.backend_application.core.domain.Tag;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface StatementGateway {
    boolean existsByTagSurname(String tagSurname);
    boolean existsById(Integer id);
    boolean existsByInformationAndPriceAndTransactionDateAndTag(
            String information, BigDecimal price, LocalDateTime transactionDate, Tag tag
    );

    Statement findById(Integer id);
    List<Statement> findByTagId(Integer tagId);
    StatementInformations findStatementInformationsByFilterAndPagination(Filter filter, Pagination pagination);
    Goal findGoalByTagId(Integer tagId);

    Statement save(Statement domain);

    void removeById(Integer id);
    void removeByTagSurname(String tagSurname);
}
