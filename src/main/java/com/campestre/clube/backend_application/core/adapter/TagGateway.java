package com.campestre.clube.backend_application.core.adapter;

import com.campestre.clube.backend_application.core.domain.Tag;

import java.util.List;

public interface TagGateway {
    boolean existsById(Integer id);
    boolean existsBySurnameIgnoreCase(String surname);
    boolean existsByColor(String color);
    boolean existsBySurnameIgnoreCaseAndIdNot(String surname, Integer id);
    boolean existsByColorAndIdNot(String color, Integer id);

    Tag findBySurnameIgnoreCase(String surname);
    Tag findById(Integer id);
    List<Tag> findAll();

    void removeById(Integer id);

    Tag save(Tag tag);
}
