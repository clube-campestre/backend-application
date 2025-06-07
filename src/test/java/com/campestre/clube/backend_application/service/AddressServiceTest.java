package com.campestre.clube.backend_application.service;

import com.campestre.clube.backend_application.entity.Address;
import com.campestre.clube.backend_application.exceptions.NotFoundException;
import com.campestre.clube.backend_application.repository.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

    @InjectMocks
    private AddressService addressService;
    @Mock
    private AddressRepository addressRepository;

    private Address address;

    @BeforeEach
    void setUp() {
        address = new Address();
        address.setId(1);
        address.setCep("12345-678");
        address.setHouseNumber("10");
    }

    @Test
    @DisplayName("deve salvar Address se não existir")
    void saveIfNotExistWhenNotExists() {
        when(addressRepository.existsByStreetAndHouseNumberAndDistrictAndStateAndCityAndCepAndReferenceHouse(
                "12345-678", "10", "", "", "", "", ""
        )).thenReturn(false);
        when(addressRepository.save(address)).thenReturn(address);

        Address result = addressService.saveIfNotExist(address);

        assertEquals(address, result);
        verify(addressRepository).save(address);
    }

    @Test
    @DisplayName("deve retornar Address existente se já existir")
    void saveIfNotExistWhenExists() {
        when(addressRepository.existsByStreetAndHouseNumberAndDistrictAndStateAndCityAndCepAndReferenceHouse(
                "12345-678", "10", "", "", "", "", ""
        )).thenReturn(true);
        when(addressRepository.findByCep("12345-678")).thenReturn(address);

        Address result = addressService.saveIfNotExist(address);

        assertEquals(address, result);
        verify(addressRepository, never()).save(any());
    }

    @Test
    @DisplayName("deve atualizar Address com sucesso")
    void updateSuccessfully() {
        when(addressRepository.save(any())).thenReturn(address);

        Address newAddress = new Address();
        newAddress.setCep("99999-000");
        newAddress.setHouseNumber("20");

        Address result = addressService.update(1, newAddress);

        assertEquals(1, result.getId());
        verify(addressRepository).save(newAddress);
    }

    @Test
    @DisplayName("deve retornar todos os Address")
    void getAll() {
        List<Address> list = List.of(address);
        when(addressRepository.findAll()).thenReturn(list);

        assertEquals(list, addressService.getAll());
        verify(addressRepository).findAll();
    }

    @Test
    @DisplayName("deve buscar Address por id com sucesso")
    void getByIdSuccessfully() {
        when(addressRepository.findById(1)).thenReturn(Optional.of(address));

        Address result = addressService.getById(1);

        assertEquals(address, result);
        verify(addressRepository).findById(1);
    }

    @Test
    @DisplayName("deve lançar exceção ao buscar Address por id inexistente")
    void getByIdNotFound() {
        when(addressRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> addressService.getById(1));
        verify(addressRepository).findById(1);
    }

    @Test
    @DisplayName("deve buscar Address por cep com sucesso")
    void getByCepSuccessfully() {
        when(addressRepository.findByCep("12345-678")).thenReturn(address);

        Address result = addressService.getByCep("12345-678");

        assertEquals(address, result);
        verify(addressRepository).findByCep("12345-678");
    }

    @Test
    @DisplayName("deve lançar exceção ao buscar Address por cep inexistente")
    void getByCepNotFound() {
        when(addressRepository.findByCep("12345-678")).thenReturn(null);

        assertThrows(NotFoundException.class, () -> addressService.getByCep("12345-678"));
        verify(addressRepository).findByCep("12345-678");
    }

    @Test
    @DisplayName("deve retornar true se Address existir pelo id")
    void existByIdTrue() {
        when(addressRepository.findById(1)).thenReturn(Optional.of(address));
        assertTrue(addressService.existById(1));
    }

    @Test
    @DisplayName("deve retornar false se Address não existir pelo id")
    void existByIdFalse() {
        when(addressRepository.findById(1)).thenReturn(Optional.empty());
        assertFalse(addressService.existById(1));
    }

    @Test
    @DisplayName("deve deletar Address com sucesso")
    void deleteSuccessfully() {
        when(addressRepository.existsById(1)).thenReturn(true);
        doNothing().when(addressRepository).deleteById(1);

        addressService.delete(1);

        verify(addressRepository).deleteById(1);
    }

    @Test
    @DisplayName("deve lançar exceção ao deletar Address inexistente")
    void deleteNotFound() {
        when(addressRepository.existsById(1)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> addressService.delete(1));
        verify(addressRepository, never()).deleteById(any());
    }

    @Test
    @DisplayName("deve retornar true se Address já existir pelo cep e número")
    void addressAlreadyExistsTrue() {
        when(addressRepository.existsByStreetAndHouseNumberAndDistrictAndStateAndCityAndCepAndReferenceHouse(
                "12345-678", "10", "", "", "", "", ""
        )).thenReturn(true);
        assertTrue(addressService.addressAlreadyExists(new Address()));
    }

    @Test
    @DisplayName("deve retornar false se Address não existir pelo cep e número")
    void addressAlreadyExistsFalse() {
        when(addressRepository.existsByStreetAndHouseNumberAndDistrictAndStateAndCityAndCepAndReferenceHouse(
                "12345-678", "10", "", "", "", "", ""
        )).thenReturn(false);
        assertFalse(addressService.addressAlreadyExists(new Address()));
    }
}