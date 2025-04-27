package com.campestre.clube.backend_application.service;

import com.campestre.clube.backend_application.entity.Account;
import com.campestre.clube.backend_application.entity.Tag;
import com.campestre.clube.backend_application.entity.Unit;
import com.campestre.clube.backend_application.entity.enums.AccessTypeEnum;
import com.campestre.clube.backend_application.repository.TagRepository;
import com.campestre.clube.backend_application.repository.UnitRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Optional<Tag> genericTag = tagRepository.findBySurname("Outros");
            if (genericTag.isEmpty()) tagRepository.save(new Tag("Outros", "FFFFFF"));
    }

    @PostConstruct
    public void generateUnits() {
        Optional<Unit> panda = unitRepository.findBySurname("Panda");
            if (panda.isEmpty()) unitRepository.save(new Unit("Panda", 0, ""));
        Optional<Unit> falcao = unitRepository.findBySurname("Falcão");
            if (falcao.isEmpty()) unitRepository.save(new Unit("Falcão", 0, ""));
        Optional<Unit> lince = unitRepository.findBySurname("Lince");
            if (lince.isEmpty()) unitRepository.save(new Unit("Lince", 0, ""));
        Optional<Unit> leao = unitRepository.findBySurname("Leão");
            if (leao.isEmpty()) unitRepository.save(new Unit("Leão", 0, ""));
        Optional<Unit> aguiaReal = unitRepository.findBySurname("Aguia Real");
            if (aguiaReal.isEmpty()) unitRepository.save(new Unit("Aguia Real", 0, ""));
        Optional<Unit> tigre = unitRepository.findBySurname("Tigre");
            if (tigre.isEmpty()) unitRepository.save(new Unit("Tigre", 0, ""));
        Optional<Unit> raposa = unitRepository.findBySurname("Raposa");
            if (raposa.isEmpty()) unitRepository.save(new Unit("Raposa", 0, ""));
        Optional<Unit> urso = unitRepository.findBySurname("Urso");
            if (urso.isEmpty()) unitRepository.save(new Unit("Urso", 0, ""));
        Optional<Unit> pantera = unitRepository.findBySurname("Pantera");
            if (pantera.isEmpty()) unitRepository.save(new Unit("Pantera", 0, ""));
        Optional<Unit> lobo = unitRepository.findBySurname("Lobo");
            if (lobo.isEmpty()) unitRepository.save(new Unit("Lobo", 0, ""));
    }

    @PostConstruct
    public void generateFirstAccount() {
        if (accountService.getAll().isEmpty()) accountService.register(
                new Account("root@email.com", "1234", "Root", AccessTypeEnum.DIRETOR)
        );
    }
}

