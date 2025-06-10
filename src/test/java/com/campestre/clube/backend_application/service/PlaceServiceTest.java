package com.campestre.clube.backend_application.service;

import com.campestre.clube.backend_application.entity.Address;
import com.campestre.clube.backend_application.entity.Place;
import com.campestre.clube.backend_application.exceptions.ConflictException;
import com.campestre.clube.backend_application.exceptions.NotFoundException;
import com.campestre.clube.backend_application.repository.PlaceRepository;
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
class PlaceServiceTest {
    @InjectMocks
    private PlaceService placeService;
    @Mock
    private PlaceRepository placeRepository;
    @Mock
    private AddressService addressService;

    @Test
    @DisplayName("deve buscar Place por id com sucesso")
    void getByIdSuccessfully() {
        Place place = new Place();
        when(placeRepository.existsById(1)).thenReturn(true);
        when(placeRepository.findById(1)).thenReturn(Optional.of(place));

        assertEquals(place, placeService.getById(1));
        verify(placeRepository).existsById(1);
        verify(placeRepository).findById(1);
    }

    @Test
    @DisplayName("deve lançar NotFoundException ao buscar Place inexistente por id")
    void getByIdPlaceNotFound() {
        when(placeRepository.existsById(1)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> placeService.getById(1));
        verify(placeRepository).existsById(1);
        verify(placeRepository, never()).findById(anyInt());
    }

    @Test
    @DisplayName("deve buscar Places ordenados por rating")
    void getAllOrderedByRatingSuccessfully() {
        List<Place> places = List.of(new Place());
        when(placeRepository.findAllByOrderByRatingDesc()).thenReturn(places);

        assertEquals(places, placeService.getAllOrderedByRating());
        verify(placeRepository).findAllByOrderByRatingDesc();
    }

    @Test
    @DisplayName("deve salvar Place com sucesso")
    void saveSuccessfully() {
        Address address = new Address(1, "1", "1", "1", "1", "1", "1", "1");
        Place place = new Place(1, address, "name", 1.0, 1, "123", 1);

        when(placeRepository.existsByName("name")).thenReturn(false);
        when(addressService.addressAlreadyExists(address)).thenReturn(false);
        when(addressService.saveIfNotExist(address)).thenReturn(address);
        when(placeRepository.save(place)).thenReturn(place);

        assertEquals(place, placeService.save(place));
        verify(placeRepository).existsByName("name");
        verify(addressService).addressAlreadyExists(address);
        verify(addressService).saveIfNotExist(address);
        verify(placeRepository).save(place);
    }

    @Test
    @DisplayName("deve lançar ConflictException ao salvar Place com nome já existente")
    void savePlaceWithExistingName() {
        Address address = new Address();
        Place place = new Place(1, address, "name", 1.0, 1, "123", 1);

        when(placeRepository.existsByName("name")).thenReturn(true);

        assertThrows(ConflictException.class, () -> placeService.save(place));
        verify(placeRepository).existsByName("name");
        verify(addressService, never()).addressAlreadyExists(any());
        verify(placeRepository, never()).save(any());
    }

    @Test
    @DisplayName("deve lançar ConflictException ao salvar Place com endereço já existente")
    void savePlaceWithExistingAddress() {
        Address address = new Address();
        Place place = new Place(1, address, "name", 1.0, 1, "123", 1);

        when(placeRepository.existsByName("name")).thenReturn(false);
        when(addressService.addressAlreadyExists(address)).thenReturn(true);

        assertThrows(ConflictException.class, () -> placeService.save(place));
        verify(addressService).addressAlreadyExists(address);
        verify(placeRepository, never()).save(any());
    }

    @Test
    @DisplayName("deve atualizar Place com sucesso")
    void updateSuccessfully() {
        Address address = new Address(1, "1", "1", "1", "1", "1", "1", "1");
        Place oldPlace = new Place(1, address, "oldName", 1.0, 1, "123", 1);
        Place newPlace = new Place(1, address, "newName", 2.0, 2, "456", 2);

        when(placeRepository.existsById(1)).thenReturn(true);
        when(placeRepository.existsByNameAndIdNot("newName", 1)).thenReturn(false);
        when(addressService.update(address.getId(), address)).thenReturn(address);
        when(placeRepository.save(newPlace)).thenReturn(newPlace);

        Place result = placeService.update(1, newPlace);

        assertEquals(newPlace, result);
        verify(placeRepository).existsById(1);
        verify(placeRepository).existsByNameAndIdNot("newName", 1);
        verify(addressService).update(address.getId(), address);
        verify(placeRepository).save(newPlace);
    }

    @Test
    @DisplayName("deve lançar NotFoundException ao atualizar Place inexistente")
    void updatePlaceNotFound() {
        Address address = new Address();
        Place newPlace = new Place(1, address, "newName", 2.0, 2, "456", 2);

        when(placeRepository.existsById(1)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> placeService.update(1, newPlace));
        verify(placeRepository).existsById(1);
        verify(placeRepository, never()).save(any());
    }

    @Test
    @DisplayName("deve lançar ConflictException ao atualizar Place com nome já existente em outro id")
    void updatePlaceWithExistingName() {
        Address address = new Address();
        Place newPlace = new Place(1, address, "newName", 2.0, 2, "456", 2);

        when(placeRepository.existsById(1)).thenReturn(true);
        when(placeRepository.existsByNameAndIdNot("newName", 1)).thenReturn(true);

        assertThrows(ConflictException.class, () -> placeService.update(1, newPlace));
        verify(placeRepository).existsById(1);
        verify(placeRepository).existsByNameAndIdNot("newName", 1);
        verify(addressService, never()).update(anyInt(), any());
        verify(placeRepository, never()).save(any());
    }

    @Test
    @DisplayName("deve deletar Place com sucesso")
    void deleteSuccessfully() {
        when(placeRepository.existsById(1)).thenReturn(true);
        doNothing().when(placeRepository).deleteById(1);

        assertDoesNotThrow(() -> placeService.delete(1));
        verify(placeRepository).existsById(1);
        verify(placeRepository).deleteById(1);
    }

    @Test
    @DisplayName("deve lançar NotFoundException ao tentar deletar Place inexistente")
    void deletePlaceNotFound() {
        when(placeRepository.existsById(1)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> placeService.delete(1));
        verify(placeRepository).existsById(1);
        verify(placeRepository, never()).deleteById(anyInt());
    }
}