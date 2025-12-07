package com.campestre.clube.backend_application.infrastructure.persistence.jpa.statement;

import com.campestre.clube.backend_application.core.adapter.StatementGateway;
import com.campestre.clube.backend_application.core.application.statement.valueobject.Filter;
import com.campestre.clube.backend_application.core.application.valueobject.Pagination;
import com.campestre.clube.backend_application.core.domain.Goal;
import com.campestre.clube.backend_application.core.domain.Statement;
import com.campestre.clube.backend_application.core.domain.StatementInformations;
import com.campestre.clube.backend_application.core.domain.Tag;
import com.campestre.clube.backend_application.infrastructure.persistence.jpa.tag.TagEntityMapper;
import com.campestre.clube.backend_application.infrastructure.persistence.jpa.tag.TagJpaAdapter;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Repository
public class StatementJpaAdapter implements StatementGateway {

    private final StatementJpaRepository repository;
    private final TagJpaAdapter tagAdapter;

    public StatementJpaAdapter(StatementJpaRepository repository,  TagJpaAdapter tagAdapter) {
        this.repository = repository;
        this.tagAdapter = tagAdapter;
    }

    @Override
    public boolean existsByTagSurname(String tagSurname) {
        return repository.existsByTagSurname(tagSurname);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public boolean existsByInformationAndPriceAndTransactionDateAndTag(
            String information, BigDecimal price, Instant transactionDate, Tag tag
    ) {
        return repository.existsByInformationAndPriceAndTransactionDateAndTag(
                information, price, transactionDate, TagEntityMapper.toEntity(tag)
        );
    }

    @Override
    @Cacheable(cacheNames = "statement.byId", key = "#id")
    public Statement findById(Long id) {
        return StatementEntityMapper.toDomain(repository.findById(id).get());
    }

    @Override
    @Cacheable(cacheNames = "statement.byTagId", key = "#tagId")
    public List<Statement> findByTagId(Long tagId) {
        return StatementEntityMapper.toDomain(repository.findAllByTag(
                TagEntityMapper.toEntity(tagAdapter.findById(tagId))
        ));
    }

    @Override
    @Cacheable(
            cacheNames = "statementInformation.byFilterAndPagination",
            key = "#filter + '-' + #pagination.pageNumber + '-' + #pagination.pageSize"
    )
    public StatementInformations findStatementInformationsByFilterAndPagination(Filter filter, Pagination pagination) {
        return new StatementInformations(
                StatementEntityMapper.toDomain(repository.findByFilterAndPagination(
                        filter.startDate(), filter.endDate(), filter.tagId(), filter.type(), filter.description(),
                        PageRequest.of(pagination.getPageNumber(), pagination.getPageSize())
                ).getContent()),
                pagination, repository.findAllPrices()
        );
    }

    @Override
    @Cacheable(cacheNames = "goal.byTagId", key = "#tagId")
    public Goal findGoalByTagId(Long tagId) {
        return Goal.of(
                repository.findAllPricesByTagId(tagId),
                tagAdapter.findById(tagId)
        );
    }

    @Override
    @CacheEvict(cacheNames = {
            "statement.byId", "statement.byTagId", "statementInformation.byFilterAndPagination", "goal.byTagId"
    }, allEntries = true)
    public void removeByTagSurname(String tagSurname) {
        repository.deleteByTag(TagEntityMapper.toEntity(tagAdapter.findBySurnameIgnoreCase(tagSurname)));
    }

    @Override
    @CacheEvict(cacheNames = {
            "statement.byId", "statement.byTagId", "statementInformation.byFilterAndPagination", "goal.byTagId"
    }, allEntries = true)
    public Statement save(Statement domain) {
        return StatementEntityMapper.toDomain(repository.save(StatementEntityMapper.toEntity(domain)));
    }

    @Override
    @CacheEvict(cacheNames = {
            "statement.byId", "statement.byTagId", "statementInformation.byFilterAndPagination", "goal.byTagId"
    }, allEntries = true)
    public void removeById(Long id) {
        repository.deleteById(id);
    }
}
