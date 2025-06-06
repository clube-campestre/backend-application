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
import org.mockito.Mockito;
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
        when(transportRepository.findById(anyInt())).thenReturn(Optional.of(transport));

        assertEquals(transport, transportService.getById(1));
        verify(transportRepository, times(1)).findById(anyInt());
    }

    @Test
    @DisplayName("deve lançar exceção ao buscar Transport por id inexistente")
    void getByIdNotFound() {
        when(transportRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> transportService.getById(1));
        verify(transportRepository, times(1)).findById(anyInt());
    }

    @Test
    @DisplayName("deve buscar todos os Transport ordenados por rating")
    void getAllOrderedByRatingSuccessfully() {
        List<Transport> transports = List.of(new Transport());
        when(transportRepository.findAllByOrderByRatingDesc()).thenReturn(transports);

        assertEquals(transports, transportService.getAllOrderedByRating());
        verify(transportRepository, times(1)).findAllByOrderByRatingDesc();
    }

    @Test
    @DisplayName("deve registrar Transport com sucesso")
    void registerSuccessfully() {
        Transport transport = new Transport();
        transport.setEnterprise("Empresa");
        when(transportRepository.existsTransportByEnterpriseContainsIgnoreCase(anyString())).thenReturn(false);
        when(transportRepository.save(any())).thenReturn(transport);

        assertEquals(transport, transportService.register(transport));
        verify(transportRepository, times(1)).existsTransportByEnterpriseContainsIgnoreCase("Empresa");
        verify(transportRepository, times(1)).save(transport);
    }

    @Test
    @DisplayName("deve lançar exceção ao registrar Transport com enterprise já existente")
    void registerWithExistingEnterprise() {
        Transport transport = new Transport();
        transport.setEnterprise("Empresa");
        when(transportRepository.existsTransportByEnterpriseContainsIgnoreCase(anyString())).thenReturn(true);

        assertThrows(ConflictException.class, () -> transportService.register(transport));
        verify(transportRepository, times(1)).existsTransportByEnterpriseContainsIgnoreCase("Empresa");
        verify(transportRepository, never()).save(any());
    }

    @Test
    @DisplayName("deve atualizar Transport com sucesso")
    void updateSuccessfully() {
        Transport oldTransport = new Transport();
        oldTransport.setId(1);
        oldTransport.setEnterprise("Empresa Antiga");

        Transport newTransport = new Transport();
        newTransport.setEnterprise("Empresa Nova");

        when(transportRepository.findById(anyInt())).thenReturn(Optional.of(oldTransport));
        when(transportRepository.existsTransportByEnterpriseAndIdNot(anyString(), anyInt())).thenReturn(false);
        when(transportRepository.save(any())).thenReturn(newTransport);

        Transport result = transportService.update(1, newTransport);

        assertEquals(newTransport, result);
        assertEquals(1, newTransport.getId());
        verify(transportRepository, times(1)).findById(1);
        verify(transportRepository, times(1)).existsTransportByEnterpriseAndIdNot("Empresa Nova", 1);
        verify(transportRepository, times(1)).save(newTransport);
    }

    @Test
    @DisplayName("deve lançar exceção ao atualizar Transport inexistente")
    void updateNotFound() {
        Transport newTransport = new Transport();
        when(transportRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> transportService.update(1, newTransport));
        verify(transportRepository, times(1)).findById(1);
        verify(transportRepository, never()).existsTransportByEnterpriseAndIdNot(anyString(), anyInt());
        verify(transportRepository, never()).save(any());
    }

    @Test
    @DisplayName("deve lançar exceção ao atualizar Transport com enterprise já existente em outro id")
    void updateWithExistingEnterprise() {
        Transport oldTransport = new Transport();
        oldTransport.setId(1);
        oldTransport.setEnterprise("Empresa Antiga");

        Transport newTransport = new Transport();
        newTransport.setEnterprise("Empresa Nova");

        when(transportRepository.findById(anyInt())).thenReturn(Optional.of(oldTransport));
        when(transportRepository.existsTransportByEnterpriseAndIdNot(anyString(), anyInt())).thenReturn(true);

        assertThrows(ConflictException.class, () -> transportService.update(1, newTransport));
        verify(transportRepository, times(1)).findById(1);
        verify(transportRepository, times(1)).existsTransportByEnterpriseAndIdNot("Empresa Nova", 1);
        verify(transportRepository, never()).save(any());
    }

    @Test
    @DisplayName("deve deletar Transport com sucesso")
    void deleteSuccessfully() {
        when(transportRepository.existsById(anyInt())).thenReturn(true);
        doNothing().when(transportRepository).deleteById(anyInt());

        transportService.delete(1);

        verify(transportRepository, times(1)).existsById(1);
        verify(transportRepository, times(1)).deleteById(1);
    }

    @Test
    @DisplayName("deve lançar exceção ao deletar Transport inexistente")
    void deleteNotFound() {
        when(transportRepository.existsById(anyInt())).thenReturn(false);

        assertThrows(NotFoundException.class, () -> transportService.delete(1));
        verify(transportRepository, times(1)).existsById(1);
        verify(transportRepository, never()).deleteById(anyInt());
    }
}