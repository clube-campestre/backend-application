package com.campestre.clube.backend_application.service;

import com.campestre.clube.backend_application.entity.Account;
import com.campestre.clube.backend_application.entity.Tag;
import com.campestre.clube.backend_application.entity.Unit;
import com.campestre.clube.backend_application.entity.enums.AccessTypeEnum;
import com.campestre.clube.backend_application.repository.TagRepository;
import com.campestre.clube.backend_application.repository.UnitRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InitServiceTest {

    @InjectMocks
    private InitService initService;
    @Mock
    private TagRepository tagRepository;
    @Mock
    private UnitRepository unitRepository;
    @Mock
    private AccountService accountService;

    @Test
    @DisplayName("deve criar Tag genérica se não existir")
    void generateGenericTagWhenNotExists() {
        when(tagRepository.findBySurname("Outros")).thenReturn(Optional.empty());
        when(tagRepository.save(any())).thenReturn(new Tag("Outros", "FFFFFF"));

        initService.generateGenericTag();

        verify(tagRepository).findBySurname("Outros");
        verify(tagRepository).save(any(Tag.class));
    }

    @Test
    @DisplayName("não deve criar Tag genérica se já existir")
    void generateGenericTagWhenExists() {
        when(tagRepository.findBySurname("Outros")).thenReturn(Optional.of(new Tag("Outros", "FFFFFF")));

        initService.generateGenericTag();

        verify(tagRepository).findBySurname("Outros");
        verify(tagRepository, never()).save(any());
    }

    @Test
    @DisplayName("deve criar todas as Units que não existem")
    void generateUnitsWhenNotExists() {
        String[] units = {"Panda", "Falcão", "Lince", "Leão", "Aguia Real", "Tigre", "Raposa", "Urso", "Pantera", "Lobo"};
        for (String name : units) {
            when(unitRepository.findBySurname(name)).thenReturn(Optional.empty());
            when(unitRepository.save(any())).thenReturn(new Unit(name, 0, ""));
        }

        initService.generateUnits();

        for (String name : units) {
            verify(unitRepository).findBySurname(name);
            verify(unitRepository).save(argThat(u -> u.getSurname().equals(name)));
        }
    }

    @Test
    @DisplayName("não deve criar Units que já existem")
    void generateUnitsWhenExists() {
        String[] units = {"Panda", "Falcão", "Lince", "Leão", "Aguia Real", "Tigre", "Raposa", "Urso", "Pantera", "Lobo"};
        for (String name : units) {
            when(unitRepository.findBySurname(name)).thenReturn(Optional.of(new Unit(name, 0, "")));
        }

        initService.generateUnits();

        for (String name : units) {
            verify(unitRepository).findBySurname(name);
        }
        verify(unitRepository, never()).save(any());
    }

    @Test
    @DisplayName("deve criar conta root se não existir nenhuma conta")
    void generateFirstAccountWhenNoAccounts() {
        when(accountService.getAll()).thenReturn(Collections.emptyList());
        when(accountService.register(any())).thenReturn(new Account("root@email.com", "1234", "Root", AccessTypeEnum.DIRETOR));

        initService.generateFirstAccount();

        verify(accountService).getAll();
        verify(accountService).register(any(Account.class));
    }

    @Test
    @DisplayName("não deve criar conta root se já existir conta")
    void generateFirstAccountWhenAccountsExist() {
        when(accountService.getAll()).thenReturn(Collections.singletonList(new Account()));

        initService.generateFirstAccount();

        verify(accountService).getAll();
        verify(accountService, never()).register(any());
    }
}