package com.campestre.clube.backend_application.service;

import com.campestre.clube.backend_application.entity.MedicalData;
import com.campestre.clube.backend_application.exceptions.BadRequestException;
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
    @DisplayName("deve lançar BadRequestException ao salvar se CPF for nulo")
    void saveThrowsIfCpfNull() {
        MedicalData data = new MedicalData();
        data.setCpf(null);
        assertThrows(BadRequestException.class, () -> service.save(data));
        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("deve lançar ConflictException ao salvar se CPF já existir")
    void saveThrowsIfCpfExists() {
        MedicalData data = new MedicalData();
        data.setCpf("123");
        when(repository.existsByCpf("123")).thenReturn(true);
        assertThrows(ConflictException.class, () -> service.save(data));
        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("deve retornar MedicalData ao salvar se não houver conflito")
    void saveSuccessfully() {
        MedicalData data = new MedicalData();
        data.setCpf("123");
        when(repository.existsByCpf("123")).thenReturn(false);
        when(repository.save(data)).thenReturn(data);
        MedicalData result = service.save(data);
        assertEquals(data, result);
        verify(repository).save(data);
    }

    @Test
    @DisplayName("deve lançar BadRequestException ao atualizar se CPF for nulo")
    void updateThrowsIfCpfNull() {
        MedicalData data = new MedicalData();
        data.setCpf(null);
        assertThrows(BadRequestException.class, () -> service.update(data));
        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("deve lançar NotFoundException ao atualizar se CPF não existir")
    void updateThrowsIfCpfNotFound() {
        MedicalData data = new MedicalData();
        data.setCpf("123");
        when(repository.existsById("123")).thenReturn(false);
        assertThrows(NotFoundException.class, () -> service.update(data));
        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("deve atualizar MedicalData com sucesso")
    void updateSuccessfully() {
        MedicalData data = new MedicalData();
        data.setCpf("123");
        when(repository.existsById("123")).thenReturn(true);
        when(repository.save(data)).thenReturn(data);
        MedicalData result = service.update(data);
        assertEquals(data, result);
        verify(repository).save(data);
    }

    @Test
    @DisplayName("deve retornar MedicalData ao buscar por id existente")
    void getByIdSuccessfully() {
        MedicalData data = new MedicalData();
        when(repository.existsById("123")).thenReturn(true);
        when(repository.findById("123")).thenReturn(Optional.of(data));
        MedicalData result = service.getById("123");
        assertEquals(data, result);
    }

    @Test
    @DisplayName("deve lançar NotFoundException ao buscar por id inexistente")
    void getByIdNotFound() {
        when(repository.existsById("123")).thenReturn(false);
        assertThrows(NotFoundException.class, () -> service.getById("123"));
        verify(repository, never()).findById(any());
    }

    @Test
    @DisplayName("deve retornar true se existir por CNS")
    void existsByCnsTrue() {
        when(repository.existsByCns("cns123")).thenReturn(true);
        assertTrue(service.existsByCns("cns123"));
    }

    @Test
    @DisplayName("deve retornar false se não existir por CNS")
    void existsByCnsFalse() {
        when(repository.existsByCns("cns123")).thenReturn(false);
        assertFalse(service.existsByCns("cns123"));
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