package com.campestre.clube.backend_application.service;

import com.campestre.clube.backend_application.exceptions.ConflictException;
import com.campestre.clube.backend_application.exceptions.NotFoundException;
import com.campestre.clube.backend_application.entity.Tag;
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

    public Tag register(Tag tag){
        if(tagRepository.existsBySurnameOrColorContains(tag.getSurname(), tag.getColor()))
            throw new ConflictException("Tag with existing surname or color");

        return tagRepository.save(tag);
    }

    public List<Tag> getAll(){
        return tagRepository.findAll();
    }

    public Tag getById(Integer id){
        return validateTagExists(id);
    }

    public Tag update(Tag tag, Integer id){
        Tag updateTag = validateTagExists(id);

//        updateTag.setId(id);
        updateTag.setSurname(tag.getSurname());
        updateTag.setColor(tag.getColor());

        return tagRepository.save(updateTag);
    }

    public void delete(Integer id){
        Tag tagToBeDeleted = validateTagExists(id);
        Tag tagGenerica = validateTagExists(3);
        statementService.updateAllForTagId(tagToBeDeleted, tagGenerica);
        tagRepository.deleteById(id);
    }

    private Tag validateGenericTag() {
        return tagRepository.findBySurname("Outros")
                .orElseThrow(() -> new NotFoundException("Generic Tag not Found"));
    }
    private Tag validateTagExists(Integer id) {
        return tagRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tag by id [%s] not found".formatted(id)));
    }
}
