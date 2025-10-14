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
    boolean existsById(Long id);
    boolean existsByInformationAndPriceAndTransactionDateAndTag(
            String information, BigDecimal price, LocalDateTime transactionDate, Tag tag
    );

    Statement findById(Long id);
    List<Statement> findByTagId(Long tagId);
    StatementInformations findStatementInformationsByFilterAndPagination(Filter filter, Pagination pagination);
    Goal findGoalByTagId(Long tagId);

    Statement save(Statement domain);

    void removeById(Long id);
    void removeByTagSurname(String tagSurname);
}
