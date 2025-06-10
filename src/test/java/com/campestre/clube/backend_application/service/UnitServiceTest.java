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
import static org.mockito.ArgumentMatchers.*;
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
        unit.setSurname(UnitEnum.PANDA.name().replace("_", " "));
        unit.setScore(10);
    }

    @Test
    @DisplayName("Deve atualizar o score da Unit com sucesso")
    void updateScoreByIdSuccessfully() {
        when(unitRepository.findById(1)).thenReturn(Optional.of(unit));
        when(unitRepository.save(any())).thenReturn(unit);

        Unit result = unitService.updateScoreById(1, 20);

        assertEquals(20, result.getScore());
        verify(unitRepository).findById(1);
        verify(unitRepository).save(unit);
    }

    @Test
    @DisplayName("Deve lançar NotFoundException ao atualizar Unit inexistente")
    void updateScoreByIdNotFound() {
        when(unitRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> unitService.updateScoreById(1, 10));
        verify(unitRepository).findById(1);
        verify(unitRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve aumentar o score da Unit com sucesso")
    void increaseScoreSuccessfully() {
        when(unitRepository.findById(1)).thenReturn(Optional.of(unit));
        when(unitRepository.save(any())).thenReturn(unit);

        Unit result = unitService.increaseOrDecreaseTheScoreById(1, 5, true);

        assertEquals(15, result.getScore());
        verify(unitRepository).findById(1);
        verify(unitRepository).save(unit);
    }

    @Test
    @DisplayName("Deve diminuir o score da Unit com sucesso")
    void decreaseScoreSuccessfully() {
        when(unitRepository.findById(1)).thenReturn(Optional.of(unit));
        when(unitRepository.save(any())).thenReturn(unit);

        Unit result = unitService.increaseOrDecreaseTheScoreById(1, 3, false);

        assertEquals(7, result.getScore());
        verify(unitRepository).findById(1);
        verify(unitRepository).save(unit);
    }

    @Test
    @DisplayName("Deve lançar NotFoundException ao aumentar/diminuir score de Unit inexistente")
    void increaseOrDecreaseScoreNotFound() {
        when(unitRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> unitService.increaseOrDecreaseTheScoreById(1, 5, true));
        verify(unitRepository).findById(1);
        verify(unitRepository, never()).save(any());
    }


    @Test
    @DisplayName("Deve resetar todos os scores das Units existentes e listar as não encontradas")
    void resetAllScores() {
        UnitEnum[] values = UnitEnum.values();
        for (UnitEnum unitEnum : values) {
            String name = unitEnum.name().replace("_", " ");
            when(unitRepository.findBySurnameIgnoreCase(name)).thenReturn(Optional.of(unit));
        }
        when(unitRepository.save(any())).thenReturn(unit);

        Pair<List<String>, List<String>> result = unitService.resetAllScores();

        assertEquals(values.length, result.a.size());
        assertEquals(0, result.b.size());
        verify(unitRepository, atLeastOnce()).findBySurnameIgnoreCase(anyString());
        verify(unitRepository, atLeastOnce()).save(any());
    }

    @Test
    @DisplayName("Deve listar Units não encontradas ao resetar scores")
    void resetAllScoresWithMissingUnits() {
        UnitEnum[] values = UnitEnum.values();
        for (UnitEnum unitEnum : values) {
            String name = unitEnum.name().replace("_", " ");
            when(unitRepository.findBySurnameIgnoreCase(name)).thenReturn(Optional.empty());
        }

        Pair<List<String>, List<String>> result = unitService.resetAllScores();

        assertEquals(0, result.a.size());
        assertEquals(values.length, result.b.size());
        verify(unitRepository, atLeastOnce()).findBySurnameIgnoreCase(anyString());
        verify(unitRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve retornar lista de Units ranqueadas e formatar o nome")
    void getRankedSuccessfully() {
        Unit unit1 = new Unit();
        unit1.setId(1);
        unit1.setSurname(UnitEnum.PANDA.name());
        unit1.setScore(20);

        Unit unit2 = new Unit();
        unit2.setId(2);
        unit2.setSurname(UnitEnum.LEAO.name());
        unit2.setScore(10);

        List<Unit> units = List.of(unit1, unit2);
        when(unitRepository.findAllByOrderByScoreDesc()).thenReturn(units);

        List<Unit> result = unitService.getRanked();

        assertEquals(2, result.size());
        assertEquals(UnitEnum.PANDA.getFormattedValue(), result.get(0).getSurname());
        assertEquals(UnitEnum.LEAO.getFormattedValue(), result.get(1).getSurname());
        verify(unitRepository).findAllByOrderByScoreDesc();
    }

    @Test
    @DisplayName("Deve lançar NotFoundException ao buscar Unit por id inexistente")
    void findByIdOrThrowNotFound() {
        when(unitRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> unitService.findByIdOrThrow(99));
        verify(unitRepository).findById(99);
    }

    @Test
    @DisplayName("Deve retornar Unit ao buscar por id existente")
    void findByIdOrThrowSuccessfully() {
        when(unitRepository.findById(1)).thenReturn(Optional.of(unit));

        Unit result = unitService.findByIdOrThrow(1);

        assertEquals(unit, result);
        verify(unitRepository).findById(1);
    }

    @Test
    @DisplayName("Deve lançar NotFoundException ao buscar Unit por surname inexistente")
    void findBySurnameOrThrowNotFound() {
        when(unitRepository.findBySurnameIgnoreCase("inexistente")).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> unitService.findBySurnameOrThrow("inexistente"));
        verify(unitRepository).findBySurnameIgnoreCase("inexistente");
    }

    @Test
    @DisplayName("Deve retornar Unit ao buscar por surname existente")
    void findBySurnameOrThrowSuccessfully() {
        when(unitRepository.findBySurnameIgnoreCase(unit.getSurname())).thenReturn(Optional.of(unit));

        Unit result = unitService.findBySurnameOrThrow(unit.getSurname());

        assertEquals(unit, result);
        verify(unitRepository).findBySurnameIgnoreCase(unit.getSurname());
    }
}