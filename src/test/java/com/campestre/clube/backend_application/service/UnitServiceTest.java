package com.campestre.clube.backend_application.service;

import com.campestre.clube.backend_application.entity.Unit;
import com.campestre.clube.backend_application.entity.enums.UnitEnum;
import com.campestre.clube.backend_application.exceptions.BadRequestException;
import com.campestre.clube.backend_application.exceptions.NotFoundException;
import com.campestre.clube.backend_application.repository.UnitRepository;
import org.antlr.v4.runtime.misc.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UnitServiceTest {


    @InjectMocks
    private UnitService unitService;

    @Mock
    private UnitRepository unitRepository;

    private Unit unit;

    @BeforeEach
    void setUp() {
        unit = new Unit();
        unit.setId(1);
        unit.setSurname("TEST UNIT");
        unit.setScore(10);
    }

    @Test
    @DisplayName("Deve atualizar o score da Unit com sucesso")
    void updateSuccessfully() {
        when(unitRepository.findById(1)).thenReturn(Optional.of(unit));
        when(unitRepository.save(any())).thenReturn(unit);

        Unit result = unitService.update(1, 20);

        assertEquals(20, result.getScore());
        verify(unitRepository).findById(1);
        verify(unitRepository).save(unit);
    }

    @Test
    @DisplayName("Deve lançar NotFoundException ao atualizar Unit inexistente")
    void updateUnitNotFound() {
        when(unitRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> unitService.update(1, 10));
        verify(unitRepository).findById(1);
        verify(unitRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao atualizar com score nulo")
    void updateWithNullScore() {
        when(unitRepository.findById(1)).thenReturn(Optional.of(unit));

        assertThrows(BadRequestException.class, () -> unitService.update(1, null));
        verify(unitRepository).findById(1);
        verify(unitRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve lançar BadRequestException ao atualizar com score negativo")
    void updateWithNegativeScore() {
        when(unitRepository.findById(1)).thenReturn(Optional.of(unit));

        assertThrows(BadRequestException.class, () -> unitService.update(1, -5));
        verify(unitRepository).findById(1);
        verify(unitRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve resetar todos os scores das Units existentes e listar as não encontradas")
    void resetAllScores() {
        // Simula que só existe uma UnitEnum e ela existe no banco
        UnitEnum[] values = UnitEnum.values();
        for (UnitEnum unitEnum : values) {
            String name = unitEnum.name().replace("_", " ");
            when(unitRepository.findBySurname(name)).thenReturn(Optional.of(unit));
        }
        when(unitRepository.save(any())).thenReturn(unit);

        Pair<List<String>, List<String>> result = unitService.resetAllScores();

        assertEquals(values.length, result.a.size());
        assertEquals(0, result.b.size());
        verify(unitRepository, atLeastOnce()).findBySurname(anyString());
        verify(unitRepository, atLeastOnce()).save(any());
    }

    @Test
    @DisplayName("Deve listar Units não encontradas ao resetar scores")
    void resetAllScoresWithMissingUnits() {
        UnitEnum[] values = UnitEnum.values();
        // Simula que nenhuma UnitEnum existe no banco
        for (UnitEnum unitEnum : values) {
            String name = unitEnum.name().replace("_", " ");
            when(unitRepository.findBySurname(name)).thenReturn(Optional.empty());
        }

        Pair<List<String>, List<String>> result = unitService.resetAllScores();

        assertEquals(0, result.a.size());
        assertEquals(values.length, result.b.size());
        verify(unitRepository, atLeastOnce()).findBySurname(anyString());
        verify(unitRepository, never()).save(any());
    }


}