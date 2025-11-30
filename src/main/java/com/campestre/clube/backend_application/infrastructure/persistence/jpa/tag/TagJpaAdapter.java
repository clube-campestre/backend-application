package com.campestre.clube.backend_application.infrastructure.persistence.jpa.tag;

import com.campestre.clube.backend_application.core.adapter.TagGateway;
import com.campestre.clube.backend_application.core.domain.Tag;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TagJpaAdapter implements TagGateway {

    private final TagJpaRepository repository;

    public TagJpaAdapter(TagJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public boolean existsBySurnameIgnoreCase(String surname) {
        return repository.existsBySurnameIgnoreCase(surname);
    }

    @Override
    public boolean existsByColor(String color) {
        return repository.existsByColor(color);
    }

    @Override
    public boolean existsBySurnameIgnoreCaseAndIdNot(String surname, Long id) {
        return repository.existsBySurnameIgnoreCaseAndIdNot(surname, id);
    }

    @Override
    public boolean existsByColorAndIdNot(String color, Long id) {
        return repository.existsByColorAndIdNot(color, id);
    }

    @Override
    @Cacheable(cacheNames = "tag.BySurnameIgnoreCase", key = "#surname")
    public Tag findBySurnameIgnoreCase(String surname) {
        return TagEntityMapper.toDomain(repository.findBySurnameIgnoreCase(surname));
    }

    @Override
    @Cacheable(cacheNames = "tag.byId", key = "#id")
    public Tag findById(Long id) {
        return TagEntityMapper.toDomain(repository.findById(id).get());
    }

    @Override
    @Cacheable(cacheNames = "tag.list")
    public List<Tag> findAll() {
        return TagEntityMapper.toDomain(repository.findAll());
    }

    @Override
    @CacheEvict(cacheNames = {"tag.BySurnameIgnoreCase", "tag.byId", "tag.list"}, allEntries = true)
    public void removeById(Long id) {
        repository.deleteById(id);
    }

    @Override
    @CacheEvict(cacheNames = {"tag.BySurnameIgnoreCase", "tag.byId", "tag.list"}, allEntries = true)
    public Tag save(Tag tag) {
        return TagEntityMapper.toDomain(repository.save(TagEntityMapper.toEntity(tag)));
    }
}
