package com.campestre.clube.backend_application.service;

import com.campestre.clube.backend_application.exceptions.ConflictException;
import com.campestre.clube.backend_application.exceptions.NotFoundException;
import com.campestre.clube.backend_application.entity.Tag;
import com.campestre.clube.backend_application.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public Tag register(Tag tag){
        if(tagRepository.existsBySurnameOrColorContains(tag.getSurname(), tag.getColor()))
            throw new ConflictException("Tag with existing surname or color");

        return tagRepository.save(tag);
    }

    public List<Tag> getAll(){
        return tagRepository.findAll();
    }

    public Tag getById(Integer id){
        Optional<Tag> tag = tagRepository.findById(id);
        tagNotFoundValidation(tag, id);

        return tag.get();
    }

    public Tag update(Tag tag, Integer id){
        Optional<Tag> updateTag = tagRepository.findById(id);
        tagNotFoundValidation(updateTag, id);

        updateTag.get().setId(id);
        updateTag.get().setSurname(tag.getSurname());
        updateTag.get().setColor(tag.getColor());

        return updateTag.get();
    }

    public void delete(Integer id){
        if(!tagRepository.existsById(id))
            throw new  NotFoundException("Tag by id [%s] not found".formatted(id));

        tagRepository.deleteById(id);
    }

    private void tagNotFoundValidation(Optional<Tag> tag, Integer id){
        if(tag.isEmpty())
            throw new NotFoundException("Tag by id [%s] not found".formatted(id));
    }

}
