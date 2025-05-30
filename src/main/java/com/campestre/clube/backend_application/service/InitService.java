package com.campestre.clube.backend_application.service;

import com.campestre.clube.backend_application.entity.Account;
import com.campestre.clube.backend_application.entity.Tag;
import com.campestre.clube.backend_application.entity.Unit;
import com.campestre.clube.backend_application.entity.enums.AccessTypeEnum;
import com.campestre.clube.backend_application.entity.enums.UnitEnum;
import com.campestre.clube.backend_application.repository.TagRepository;
import com.campestre.clube.backend_application.repository.UnitRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class InitService {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private UnitRepository unitRepository;

    @Autowired
    private AccountService accountService;

    @PostConstruct
    public void generateGenericTag() {
        Optional<Tag> genericTag = tagRepository.findBySurnameIgnoreCase("OUTROS");
            if (genericTag.isEmpty()) tagRepository.save(new Tag("OUTROS", "FFFFFF", null, null));
    }

    @PostConstruct
    public void generateUnits() {
        Arrays.stream(UnitEnum.values()).forEach(unit -> saveUnitIfNotExist(unit.name()));
    }

    private void saveUnitIfNotExist(String name) {
        if (!unitRepository.existsBySurnameIgnoreCase(name)) unitRepository.save(new Unit(name, 0));
    }

    @PostConstruct
    public void generateFirstAccount() {
        if (accountService.getAll().isEmpty()) accountService.register(
                new Account("root@email.com", "1234", "Root", AccessTypeEnum.DIRETOR)
        );
    }
}

