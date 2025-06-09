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
        address = new Address(
                1,
                "street",
                "10",
                "district",
                "state",
                "city",
                "12345-678",
                null
        );
    }

    @Test
    @DisplayName("deve salvar Address se não existir")
    void saveIfNotExistWhenNotExists() {
        when(addressRepository.existsByStreetAndHouseNumberAndDistrictAndStateAndCityAndCepAndReferenceHouse(
                any(), any(), any(), any(), any(), any(), any()
        )).thenReturn(false);
        when(addressRepository.save(address)).thenReturn(address);

        Address result = addressService.saveIfNotExist(address);

        assertEquals(address, result);
        verify(addressRepository).save(address);
    }

    @Test
    @DisplayName("deve retornar Address existente se já existir")
    void saveIfNotExistWhenExists() {
        Address address = new Address();
        when(addressRepository.existsByStreetAndHouseNumberAndDistrictAndStateAndCityAndCepAndReferenceHouse(
                any(), any(), any(), any(), any(), any(), any()
        )).thenReturn(true);

        when(addressRepository.findByStreetAndHouseNumberAndDistrictAndStateAndCityAndCepAndReferenceHouse(
                any(), any(), any(), any(), any(), any(), any()
        )).thenReturn(address);

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
        when(addressRepository.existsById(1)).thenReturn(true);
        when(addressRepository.findById(1)).thenReturn(Optional.of(address));

        Address result = addressService.getById(1);

        assertEquals(address, result);
        verify(addressRepository, times(1)).existsById(1);
        verify(addressRepository, times(1)).findById(1);
    }

    @Test
    @DisplayName("deve lançar exceção ao buscar Address por id inexistente")
    void getByIdNotFound() {
        when(addressRepository.existsById(1)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> addressService.getById(1));
        verify(addressRepository, times(1)).existsById(1);
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

        verify(addressRepository, times(1)).deleteById(1);
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
                any(), any(), any(), any(), any(), any(), any()
        )).thenReturn(true);
        addressService.addressAlreadyExists(address);
        assertTrue(addressService.addressAlreadyExists(new Address()));
    }

    @Test
    @DisplayName("deve retornar false se Address não existir pelo cep e número")
    void addressAlreadyExistsFalse() {
        when(addressRepository.existsByStreetAndHouseNumberAndDistrictAndStateAndCityAndCepAndReferenceHouse(
                any(), any(), any(), any(), any(), any(), any()
        )).thenReturn(false);
        addressService.addressAlreadyExists(address);
        assertFalse(addressService.addressAlreadyExists(new Address()));
    }
}