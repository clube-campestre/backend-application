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
        Optional<Tag> genericTag = tagRepository.findBySurnameIgnoreCase("OUTROS");
            if (genericTag.isEmpty()) tagRepository.save(new Tag("OUTROS", "FFFFFF", null, null));
    }

    @PostConstruct
    public void generateUnits() {
        Optional<Unit> panda = unitRepository.findBySurnameIgnoreCase("PANDA");
            if (panda.isEmpty()) unitRepository.save(new Unit("PANDA", 0, ""));
        Optional<Unit> falcao = unitRepository.findBySurnameIgnoreCase("FALCAO");
            if (falcao.isEmpty()) unitRepository.save(new Unit("FALCAO", 0, ""));
        Optional<Unit> lince = unitRepository.findBySurnameIgnoreCase("LINCE");
            if (lince.isEmpty()) unitRepository.save(new Unit("LINCE", 0, ""));
        Optional<Unit> leao = unitRepository.findBySurnameIgnoreCase("LEAO");
            if (leao.isEmpty()) unitRepository.save(new Unit("LEAO", 0, ""));
        Optional<Unit> aguiaReal = unitRepository.findBySurnameIgnoreCase("AGUIA_REAL");
            if (aguiaReal.isEmpty()) unitRepository.save(new Unit("AGUIA_REAL", 0, ""));
        Optional<Unit> tigre = unitRepository.findBySurnameIgnoreCase("TIGRE");
            if (tigre.isEmpty()) unitRepository.save(new Unit("TIGRE", 0, ""));
        Optional<Unit> raposa = unitRepository.findBySurnameIgnoreCase("RAPOSA");
            if (raposa.isEmpty()) unitRepository.save(new Unit("RAPOSA", 0, ""));
        Optional<Unit> urso = unitRepository.findBySurnameIgnoreCase("URSO");
            if (urso.isEmpty()) unitRepository.save(new Unit("URSO", 0, ""));
        Optional<Unit> pantera = unitRepository.findBySurnameIgnoreCase("PANTERA");
            if (pantera.isEmpty()) unitRepository.save(new Unit("PANTERA", 0, ""));
        Optional<Unit> lobo = unitRepository.findBySurnameIgnoreCase("LOBO");
            if (lobo.isEmpty()) unitRepository.save(new Unit("LOBO", 0, ""));
    }

    @PostConstruct
    public void generateFirstAccount() {
        if (accountService.getAll().isEmpty()) accountService.register(
                new Account("root@email.com", "1234", "Root", AccessTypeEnum.DIRETOR)
        );
    }
}

