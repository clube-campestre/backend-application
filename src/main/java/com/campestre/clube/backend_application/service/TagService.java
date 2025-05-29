package com.campestre.clube.backend_application.service;

import com.campestre.clube.backend_application.entity.Tag;
import com.campestre.clube.backend_application.exceptions.ConflictException;
import com.campestre.clube.backend_application.exceptions.NotFoundException;
import com.campestre.clube.backend_application.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private StatementService statementService;

    public Tag register(Tag tag) {
        if (tagRepository.existsBySurnameIgnoreCaseOrColorContains(tag.getSurname(), tag.getColor()))
            throw new ConflictException("Tag with existing surname or color");

        return tagRepository.save(tag);
    }

    public List<Tag> getAll() {
        return tagRepository.findAll();
    }

    public Tag getById(Integer id) {
        return findByIdOrThrow(id);
    }

    public Tag update(Tag tag, Integer id) {
        existsByIdOrThrow(id);
        tag.setId(id);
        return tagRepository.save(tag);
    }

    public void delete(Integer id) {
        statementService.updateAllForTagId(findByIdOrThrow(id), findGenericTagOrThrow());
        tagRepository.deleteById(id);
    }

    private Tag findGenericTagOrThrow() {
        return tagRepository.findBySurnameIgnoreCase("Outros")
                .orElseThrow(() -> new NotFoundException("Generic Tag not Found"));
    }

    private Tag findByIdOrThrow(Integer id) {
        return tagRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tag by id [%s] not found".formatted(id)));
    }

    private void existsByIdOrThrow(Integer id) {
        if (!tagRepository.existsById(id))
            throw new NotFoundException("Tag by id [%s] not found".formatted(id));
    }
}
