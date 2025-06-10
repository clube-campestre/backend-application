package com.campestre.clube.backend_application.service;

import com.campestre.clube.backend_application.entity.Account;
import com.campestre.clube.backend_application.entity.Tag;
import com.campestre.clube.backend_application.entity.Unit;
import com.campestre.clube.backend_application.entity.enums.AccessTypeEnum;
import com.campestre.clube.backend_application.entity.enums.UnitEnum;
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
        when(tagRepository.findBySurnameIgnoreCase("OUTROS")).thenReturn(Optional.empty());
        when(tagRepository.save(any())).thenReturn(new Tag("OUTROS", "FFFFFF", null, null));

        initService.generateGenericTag();

        verify(tagRepository).findBySurnameIgnoreCase("OUTROS");
        verify(tagRepository).save(any(Tag.class));
    }

    @Test
    @DisplayName("não deve criar Tag genérica se já existir")
    void generateGenericTagWhenExists() {
        when(tagRepository.findBySurnameIgnoreCase("OUTROS")).thenReturn(Optional.of(new Tag("OUTROS", "FFFFFF", null, null)));

        initService.generateGenericTag();

        verify(tagRepository).findBySurnameIgnoreCase("OUTROS");
        verify(tagRepository, never()).save(any());
    }

    @Test
    @DisplayName("deve criar todas as Units que não existem")
    void generateUnitsWhenNotExists() {
        for (UnitEnum unitEnum : UnitEnum.values()) {
            when(unitRepository.existsBySurnameIgnoreCase(unitEnum.name())).thenReturn(false);
            when(unitRepository.save(any())).thenReturn(new Unit(unitEnum.getId(), unitEnum.name(), 0));
        }

        initService.generateUnits();

        for (UnitEnum unitEnum : UnitEnum.values()) {
            verify(unitRepository).existsBySurnameIgnoreCase(unitEnum.name());
            verify(unitRepository).save(argThat(u -> u.getSurname().equals(unitEnum.name())));
        }
    }

    @Test
    @DisplayName("não deve criar Units que já existem")
    void generateUnitsWhenExists() {
        for (UnitEnum unitEnum : UnitEnum.values()) {
            when(unitRepository.existsBySurnameIgnoreCase(unitEnum.name())).thenReturn(true);
        }

        initService.generateUnits();

        for (UnitEnum unitEnum : UnitEnum.values()) {
            verify(unitRepository).existsBySurnameIgnoreCase(unitEnum.name());
        }
        verify(unitRepository, never()).save(any());
    }

    @Test
    @DisplayName("deve criar conta root se não existir nenhuma conta")
    void generateFirstAccountWhenNoAccounts() {
        when(accountService.getAll()).thenReturn(Collections.emptyList());
        when(accountService.register(any()))
                .thenReturn(new Account("root@email.com", "1234", "Root", AccessTypeEnum.DIRETOR));

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