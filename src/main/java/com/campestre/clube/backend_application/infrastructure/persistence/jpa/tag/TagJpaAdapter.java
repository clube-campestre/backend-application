package com.campestre.clube.backend_application.infrastructure.persistence.jpa.tag;

import com.campestre.clube.backend_application.core.adapter.TagGateway;
import com.campestre.clube.backend_application.core.domain.Tag;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TagJpaAdapter implements TagGateway {

    private final TagJpaRepository repository;

    public TagJpaAdapter(TagJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean existsById(Integer id) {
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
    public boolean existsBySurnameIgnoreCaseAndIdNot(String surname, Integer id) {
        return repository.existsBySurnameIgnoreCaseAndIdNot(surname, id);
    }

    @Override
    public boolean existsByColorAndIdNot(String color, Integer id) {
        return repository.existsByColorAndIdNot(color, id);
    }

    @Override
    public Tag findBySurnameIgnoreCase(String surname) {
        return TagEntityMapper.toDomain(repository.findBySurnameIgnoreCase(surname));
    }

    @Override
    public Tag findById(Integer id) {
        return TagEntityMapper.toDomain(repository.findById(id).get());
    }

    @Override
    public List<Tag> findAll() {
        return TagEntityMapper.toDomain(repository.findAll());
    }

    @Override
    public void removeById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Tag save(Tag tag) {
        return TagEntityMapper.toDomain(repository.save(TagEntityMapper.toEntity(tag)));
    }
}
