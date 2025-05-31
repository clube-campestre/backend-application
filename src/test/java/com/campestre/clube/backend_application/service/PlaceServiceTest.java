package com.campestre.clube.backend_application.service;

import com.campestre.clube.backend_application.entity.Address;
import com.campestre.clube.backend_application.entity.Place;
import com.campestre.clube.backend_application.exceptions.NotFoundException;
import com.campestre.clube.backend_application.repository.PlaceRepository;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
class PlaceServiceTest {
    @InjectMocks
    private PlaceService placeService;
    @Mock
    private PlaceRepository placeRepository;
    @Mock
    private AddressService addressService;

    @Test
    @DisplayName("should search the Place by id successfully")
    void getByIdSuccessfully() {
        Optional<Place> place = Optional.of(new Place());
        when(placeRepository.findById(any())).thenReturn(place);

        assertEquals(place.get(), placeService.getById(1));
        verify(placeRepository, Mockito.times(1)).findById(any());
    }

    @Test
    @DisplayName("should search and not find the Place by id")
    void getByIdPlaceNotFound() {
        Optional<Place> place = Optional.empty();
        when(placeRepository.findById(any())).thenReturn(place);

        assertThrows(NotFoundException.class, () -> placeService.getById(1));
        verify(placeRepository, Mockito.times(1)).findById(any());
    }

    @Test
    @DisplayName("should search the Place sorted by rating successfully")
    void getAllOrderedByRatingSuccessfully() {
        List<Place> places = List.of(new Place());
        when(placeRepository.findAllByOrderByRatingDesc()).thenReturn(places);

        assertEquals(places, placeService.getAllOrderedByRating());
        verify(placeRepository, Mockito.times(1)).findAllByOrderByRatingDesc();
    }

    @Test
    @DisplayName("should save the Place successfully")
        //TODO fazer cenário ruim e depois update e delete
    void save() {
        Address address = new Address(1, "1", "1", "1", "1", "1", "1", "1");
        Place place = new Place(1, address, "name", 1.0, 1, "123", 1);

        when(placeRepository.existsByName(any())).thenReturn(false);
        when(addressService.addressAlreadyExists(any(), any())).thenReturn(false);
        when(addressService.saveIfNotExist(any())).thenReturn(address);
        when(placeRepository.save(any())).thenReturn(place);

        assertEquals(place, placeService.save(place));
        verify(placeRepository, Mockito.times(1)).existsByName(place.getName());
        verify(addressService, Mockito.times(1)).addressAlreadyExists(place.getAddress().getCep(), place.getAddress().getHouseNumber());
        verify(addressService, Mockito.times(1)).saveIfNotExist(place.getAddress());
        verify(placeRepository, Mockito.times(1)).save(place);
    }

    @Test
    @DisplayName("deve atualizar o Place com sucesso")
    void updateSuccessfully() {
        Address address = new Address(1, "1", "1", "1", "1", "1", "1", "1");
        Place oldPlace = new Place(1, address, "oldName", 1.0, 1, "123", 1);
        Place newPlace = new Place(1, address, "newName", 2.0, 2, "456", 2);

        when(placeRepository.findById(any())).thenReturn(Optional.of(oldPlace));
        when(placeRepository.existsByNameAndIdNot(any(), anyInt())).thenReturn(false);
        when(addressService.update(anyInt(), any())).thenReturn(address);
        when(placeRepository.save(any())).thenReturn(newPlace);

        Place result = placeService.update(1, newPlace);

        assertEquals(newPlace, result);
        verify(placeRepository, times(1)).findById(1);
        verify(placeRepository, times(1)).existsByNameAndIdNot(newPlace.getName(), 1);
        verify(addressService, times(1)).update(address.getId(), address);
        verify(placeRepository, times(1)).save(newPlace);
    }


    @Test
    @DisplayName("deve lançar exceção ao tentar atualizar Place com nome já existente em outro id")
    void updatePlaceWithExistingName() {
        Address address = new Address(1, "1", "1", "1", "1", "1", "1", "1");
        Place oldPlace = new Place(1, address, "oldName", 1.0, 1, "123", 1);
        Place newPlace = new Place(1, address, "newName", 2.0, 2, "456", 2);

        when(placeRepository.findById(anyInt())).thenReturn(Optional.of(oldPlace));
        when(placeRepository.existsByNameAndIdNot(any(), anyInt())).thenReturn(true);

        assertThrows(com.campestre.clube.backend_application.exceptions.ConflictException.class, () -> placeService.update(1, newPlace));
        verify(placeRepository, times(1)).findById(1);
        verify(placeRepository, times(1)).existsByNameAndIdNot(newPlace.getName(), 1);
        verify(addressService, never()).update(anyInt(), any());
        verify(placeRepository, never()).save(any());
    }

    @Test
    @DisplayName("deve lançar exceção ao tentar deletar Place inexistente")
    void deletePlaceNotFound() {
        when(placeRepository.existsById(anyInt())).thenReturn(false);

        assertThrows(NotFoundException.class, () -> placeService.delete(1));
        verify(placeRepository, times(1)).existsById(1);
        verify(placeRepository, never()).deleteById(anyInt());
    }
}

