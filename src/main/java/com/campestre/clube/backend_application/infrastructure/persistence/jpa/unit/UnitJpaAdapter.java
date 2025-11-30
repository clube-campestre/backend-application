package com.campestre.clube.backend_application.infrastructure.persistence.jpa.unit;

import com.campestre.clube.backend_application.core.adapter.UnitGateway;
import com.campestre.clube.backend_application.core.domain.Unit;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UnitJpaAdapter implements UnitGateway {

    private final UnitJpaRepository repository;

    public UnitJpaAdapter(UnitJpaRepository repository) {
        this.repository = repository;
    }


    @Override
    public boolean existsBySurnameIgnoreCase(String surname) {
        return repository.existsBySurnameIgnoreCase(surname);
    }

    @Override
    @Cacheable(cacheNames = "unit.bySurnameIgnoreCase", key = "#surname")
    public Unit findBySurnameIgnoreCase(String surname) {
        return UnitEntityMapper.toDomain(repository.findBySurnameIgnoreCase(surname).get());
    }

    @Override
    @Cacheable(cacheNames = "unit.byScoreNot", key = "#score")
    public List<Unit> findByScoreNot(Integer score) {
        return UnitEntityMapper.toDomain(repository.findByScoreNot(score));
    }

    @Override
    @Cacheable(cacheNames = "unit.list")
    public List<Unit> findOrderByScoreDesc() {
        return UnitEntityMapper.toDomain(repository.findAllByOrderByScoreDesc());
    }

    @Override
    @CacheEvict(cacheNames = {"unit.bySurnameIgnoreCase", "unit.byScoreNot", "unit.list"}, allEntries = true)
    public Unit save(Unit unit) {
        return UnitEntityMapper.toDomain(repository.save(UnitEntityMapper.toEntity(unit)));
    }
}
