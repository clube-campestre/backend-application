package com.campestre.clube.backend_application.service;

import com.campestre.clube.backend_application.entity.Transport;
import com.campestre.clube.backend_application.exceptions.ConflictException;
import com.campestre.clube.backend_application.exceptions.NotFoundException;
import com.campestre.clube.backend_application.repository.TransportRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TransportServiceTest {

    @InjectMocks
    private TransportService transportService;
    @Mock
    private TransportRepository transportRepository;

    @Test
    @DisplayName("deve buscar Transport por id com sucesso")
    void getByIdSuccessfully() {
        Transport transport = new Transport();
        when(transportRepository.existsById(1)).thenReturn(true);
        when(transportRepository.findById(1)).thenReturn(Optional.of(transport));

        assertEquals(transport, transportService.getById(1));
        verify(transportRepository).existsById(1);
        verify(transportRepository).findById(1);
    }

    @Test
    @DisplayName("deve lançar exceção ao buscar Transport por id inexistente")
    void getByIdNotFound() {
        when(transportRepository.existsById(1)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> transportService.getById(1));
        verify(transportRepository).existsById(1);
        verify(transportRepository, never()).findById(anyInt());
    }

    @Test
    @DisplayName("deve buscar todos os Transport ordenados por rating")
    void getAllOrderedByRatingSuccessfully() {
        List<Transport> transports = List.of(new Transport());
        when(transportRepository.findAllByOrderByRatingDesc()).thenReturn(transports);

        assertEquals(transports, transportService.getAllOrderedByRating());
        verify(transportRepository).findAllByOrderByRatingDesc();
    }

    @Test
    @DisplayName("deve registrar Transport com sucesso")
    void registerSuccessfully() {
        Transport transport = new Transport();
        transport.setEnterprise("Empresa");
        when(transportRepository.existsTransportByEnterpriseContainsIgnoreCase("Empresa")).thenReturn(false);
        when(transportRepository.save(transport)).thenReturn(transport);

        assertEquals(transport, transportService.register(transport));
        verify(transportRepository).existsTransportByEnterpriseContainsIgnoreCase("Empresa");
        verify(transportRepository).save(transport);
    }

    @Test
    @DisplayName("deve lançar exceção ao registrar Transport com enterprise já existente")
    void registerWithExistingEnterprise() {
        Transport transport = new Transport();
        transport.setEnterprise("Empresa");
        when(transportRepository.existsTransportByEnterpriseContainsIgnoreCase("Empresa")).thenReturn(true);

        assertThrows(ConflictException.class, () -> transportService.register(transport));
        verify(transportRepository).existsTransportByEnterpriseContainsIgnoreCase("Empresa");
        verify(transportRepository, never()).save(any());
    }

    @Test
    @DisplayName("deve atualizar Transport com sucesso")
    void updateSuccessfully() {
        Transport newTransport = new Transport();
        newTransport.setEnterprise("Empresa Nova");

        when(transportRepository.existsById(1)).thenReturn(true);
        when(transportRepository.existsTransportByEnterpriseAndIdNot("Empresa Nova", 1)).thenReturn(false);
        when(transportRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        Transport result = transportService.update(1, newTransport);

        assertEquals("Empresa Nova", result.getEnterprise());
        assertEquals(1, result.getId());
        verify(transportRepository).existsById(1);
        verify(transportRepository).existsTransportByEnterpriseAndIdNot("Empresa Nova", 1);
        verify(transportRepository).save(newTransport);
    }

    @Test
    @DisplayName("deve lançar exceção ao atualizar Transport inexistente")
    void updateNotFound() {
        Transport newTransport = new Transport();
        when(transportRepository.existsById(1)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> transportService.update(1, newTransport));
        verify(transportRepository).existsById(1);
        verify(transportRepository, never()).existsTransportByEnterpriseAndIdNot(anyString(), anyInt());
        verify(transportRepository, never()).save(any());
    }

    @Test
    @DisplayName("deve lançar exceção ao atualizar Transport com enterprise já existente em outro id")
    void updateWithExistingEnterprise() {
        Transport newTransport = new Transport();
        newTransport.setEnterprise("Empresa Nova");

        when(transportRepository.existsById(1)).thenReturn(true);
        when(transportRepository.existsTransportByEnterpriseAndIdNot("Empresa Nova", 1)).thenReturn(true);

        assertThrows(ConflictException.class, () -> transportService.update(1, newTransport));
        verify(transportRepository).existsById(1);
        verify(transportRepository).existsTransportByEnterpriseAndIdNot("Empresa Nova", 1);
        verify(transportRepository, never()).save(any());
    }

    @Test
    @DisplayName("deve deletar Transport com sucesso")
    void deleteSuccessfully() {
        when(transportRepository.existsById(1)).thenReturn(true);
        doNothing().when(transportRepository).deleteById(1);

        transportService.delete(1);

        verify(transportRepository).existsById(1);
        verify(transportRepository).deleteById(1);
    }

    @Test
    @DisplayName("deve lançar exceção ao deletar Transport inexistente")
    void deleteNotFound() {
        when(transportRepository.existsById(1)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> transportService.delete(1));
        verify(transportRepository).existsById(1);
        verify(transportRepository, never()).deleteById(anyInt());
    }
}