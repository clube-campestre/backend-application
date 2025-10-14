package com.campestre.clube.backend_application.core.adapter;

import com.campestre.clube.backend_application.core.domain.Tag;

import java.util.List;

public interface TagGateway {
    boolean existsById(Long id);
    boolean existsBySurnameIgnoreCase(String surname);
    boolean existsByColor(String color);
    boolean existsBySurnameIgnoreCaseAndIdNot(String surname, Long id);
    boolean existsByColorAndIdNot(String color, Long id);

    Tag findBySurnameIgnoreCase(String surname);
    Tag findById(Long id);
    List<Tag> findAll();

    void removeById(Long id);

    Tag save(Tag tag);
}
