package com.campestre.clube.backend_application.deprecated.service;

import com.campestre.clube.backend_application.deprecated.entity.Account;
import com.campestre.clube.backend_application.deprecated.entity.Tag;
import com.campestre.clube.backend_application.deprecated.entity.Unit;
import com.campestre.clube.backend_application.deprecated.entity.enums.AccessTypeEnum;
import com.campestre.clube.backend_application.deprecated.entity.enums.UnitEnum;
import com.campestre.clube.backend_application.deprecated.repository.TagRepository;
import com.campestre.clube.backend_application.deprecated.repository.UnitRepository;
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
        Arrays.stream(UnitEnum.values()).forEach(unit -> saveUnitIfNotExist(unit.getId(), unit.name()));
    }

    private void saveUnitIfNotExist(Integer id, String name) {
        if (!unitRepository.existsBySurnameIgnoreCase(name)) unitRepository.save(new Unit(id, name, 0));
    }

    @PostConstruct
    public void generateFirstAccount() {
        if (accountService.getAll().isEmpty()) accountService.register(
                new Account("root@email.com", "1234", "Root", AccessTypeEnum.DIRETOR)
        );
    }
}

