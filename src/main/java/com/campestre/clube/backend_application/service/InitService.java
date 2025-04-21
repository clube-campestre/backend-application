package com.campestre.clube.backend_application.service;

import com.campestre.clube.backend_application.entity.Tag;
import com.campestre.clube.backend_application.repository.TagRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InitService {


    @Autowired
    private TagRepository tagRepository;

    @PostConstruct
    public void generateGenericTag() {
        Optional<Tag> genericTag = tagRepository.findBySurname("Outros");
            if (genericTag.isEmpty()) tagRepository.save(new Tag("Outros", "FFFFFF"));
    }
}

