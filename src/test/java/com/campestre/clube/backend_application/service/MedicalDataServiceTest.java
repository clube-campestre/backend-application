package com.campestre.clube.backend_application.service;

import com.campestre.clube.backend_application.entity.MedicalData;
import com.campestre.clube.backend_application.exceptions.ConflictException;
import com.campestre.clube.backend_application.exceptions.NotFoundException;
import com.campestre.clube.backend_application.repository.MedicalDataRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MedicalDataServiceTest {

    @InjectMocks
    private MedicalDataService service;
    @Mock
    private MedicalDataRepository repository;

    @Test
    @DisplayName("deve lançar NotFoundException ao salvar se CPF não for nulo")
    void saveThrowsIfCpfNotNull() {
        MedicalData data = new MedicalData();
        data.setCpf("123");
        assertThrows(NotFoundException.class, () -> service.save(data));
        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("deve lançar ConflictException ao salvar se CPF já existir")
    void saveThrowsIfCpfExists() {
        MedicalData data = new MedicalData();
        data.setCpf(null);
        when(repository.existsById(null)).thenReturn(true);
        assertThrows(ConflictException.class, () -> service.save(data));
    }

    @Test
    @DisplayName("deve retornar MedicalData ao salvar se não houver conflito")
    void saveSuccessfully() {
        MedicalData data = new MedicalData();
        data.setCpf(null);
        when(repository.existsById(null)).thenReturn(false);
        MedicalData result = service.save(data);
        assertEquals(data, result);
    }

    @Test
    @DisplayName("deve lançar NotFoundException ao atualizar se CPF não for nulo")
    void updateThrowsIfCpfNotNull() {
        MedicalData data = new MedicalData();
        assertThrows(NotFoundException.class, () -> service.update("123", data));
    }

    @Test
    @DisplayName("deve retornar MedicalData ao atualizar se CPF for nulo")
    void updateSuccessfully() {
        MedicalData data = new MedicalData();
        MedicalData result = service.update(null, data);
        assertEquals(data, result);
    }

    @Test
    @DisplayName("deve retornar MedicalData ao buscar por id existente")
    void getByIdSuccessfully() {
        MedicalData data = new MedicalData();
        when(repository.findById("123")).thenReturn(Optional.of(data));
        MedicalData result = service.getById("123");
        assertEquals(data, result);
    }

    @Test
    @DisplayName("deve lançar NotFoundException ao buscar por id inexistente")
    void getByIdNotFound() {
        when(repository.findById("123")).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> service.getById("123"));
    }

    @Test
    @DisplayName("deve retornar true se existir por CPF")
    void existByCpfTrue() {
        when(repository.findById("123")).thenReturn(Optional.of(new MedicalData()));
        assertTrue(service.existsByCpf("123"));
    }

    @Test
    @DisplayName("deve retornar false se não existir por CPF")
    void existByCpfFalse() {
        when(repository.findById("123")).thenReturn(Optional.empty());
        assertFalse(service.existsByCpf("123"));
    }

    @Test
    @DisplayName("deve deletar com sucesso se existir")
    void deleteSuccessfully() {
        when(repository.existsById("123")).thenReturn(true);
        doNothing().when(repository).deleteById("123");
        service.delete("123");
        verify(repository).deleteById("123");
    }

    @Test
    @DisplayName("deve lançar NotFoundException ao deletar inexistente")
    void deleteNotFound() {
        when(repository.existsById("123")).thenReturn(false);
        assertThrows(NotFoundException.class, () -> service.delete("123"));
        verify(repository, never()).deleteById(any());
    }
}