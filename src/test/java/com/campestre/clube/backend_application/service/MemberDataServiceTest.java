package com.campestre.clube.backend_application.service;

import com.campestre.clube.backend_application.entity.Address;
import com.campestre.clube.backend_application.entity.MedicalData;
import com.campestre.clube.backend_application.entity.MemberData;
import com.campestre.clube.backend_application.entity.Unit;
import com.campestre.clube.backend_application.exceptions.ConflictException;
import com.campestre.clube.backend_application.exceptions.NotFoundException;
import com.campestre.clube.backend_application.repository.MemberDataRepository;
import com.campestre.clube.backend_application.repository.UnitRepository;
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
class MemberDataServiceTest {

    @InjectMocks
    private MemberDataService memberDataService;
    @Mock
    private MemberDataRepository memberDataRepository;
    @Mock
    private UnitRepository unitRepository;
    @Mock
    private AddressService addressService;
    @Mock
    private MedicalDataService medicalDataService;
    @Mock
    private DriveService driveService;

    private MemberData memberData;
    private Unit unit;
    private Address address;
    private MedicalData medicalData;

    @BeforeEach
    void setUp() {
        unit = new Unit();
        unit.setId(1);

        address = new Address();
        address.setId(1);

        medicalData = new MedicalData();
        medicalData.setCpf("123");

        memberData = new MemberData();
        memberData.setCpf("123");
        memberData.setUnit(unit);
        memberData.setAddress(address);
        memberData.setMedicalData(medicalData);
        memberData.setIdImage("imgId");
    }

    @Test
    @DisplayName("deve registrar MemberData com sucesso")
    void registerSuccessfully() {
        when(unitRepository.findById(anyInt())).thenReturn(Optional.of(unit));
        when(medicalDataService.save(any())).thenReturn(medicalData);
        when(addressService.saveIfNotExist(any())).thenReturn(address);
        when(memberDataRepository.existsByCpf(anyString())).thenReturn(false);
        when(memberDataRepository.save(any())).thenReturn(memberData);

        MemberData result = memberDataService.register(memberData);

        assertEquals(memberData, result);
        verify(unitRepository).findById(1);
        verify(medicalDataService).save(medicalData);
        verify(addressService).saveIfNotExist(address);
        verify(memberDataRepository).existsByCpf("123");
        verify(memberDataRepository).save(memberData);
    }

    @Test
    @DisplayName("deve lançar exceção ao registrar MemberData com CPF já existente")
    void registerWithExistingCpf() {
        when(unitRepository.findById(anyInt())).thenReturn(Optional.of(unit));
        when(medicalDataService.save(any())).thenReturn(medicalData);
        when(addressService.saveIfNotExist(any())).thenReturn(address);
        when(memberDataRepository.existsByCpf(anyString())).thenReturn(true);

        assertThrows(ConflictException.class, () -> memberDataService.register(memberData));
        verify(memberDataRepository, never()).save(any());
    }

    @Test
    @DisplayName("deve lançar exceção ao registrar MemberData com Unit inexistente")
    void registerWithUnitNotFound() {
        when(unitRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> memberDataService.register(memberData));
        verify(memberDataRepository, never()).save(any());
    }

    @Test
    @DisplayName("deve retornar todos os MemberData")
    void getAll() {
        List<MemberData> list = List.of(memberData);
        when(memberDataRepository.findAll()).thenReturn(list);

        assertEquals(list, memberDataService.getAll());
        verify(memberDataRepository).findAll();
    }

    @Test
    @DisplayName("deve buscar MemberData por CPF com sucesso")
    void getByIdSuccessfully() {
        when(memberDataRepository.findByCpf(anyString())).thenReturn(Optional.of(memberData));

        assertEquals(memberData, memberDataService.getById("123"));
        verify(memberDataRepository).findByCpf("123");
    }

    @Test
    @DisplayName("deve lançar exceção ao buscar MemberData por CPF inexistente")
    void getByIdNotFound() {
        when(memberDataRepository.findByCpf(anyString())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> memberDataService.getById("123"));
        verify(memberDataRepository).findByCpf("123");
    }

//    @Test
//    @DisplayName("deve buscar MemberData por unidade")
//    void getByUnit() {
//        List<MemberData> list = List.of(memberData);
//        when(memberDataRepository.findByUnitAndPagination(anyInt())).thenReturn(list);
//        when(unitRepository.findByUnitAndPagination(anyInt())).thenReturn(10);
//
//        Pair<List<MemberData>, Integer> result = memberDataService.getByUnit(1);
//
//        assertEquals(list, result.a);
//        assertEquals(10, result.b);
//        verify(memberDataRepository).findAllByUnitId(1);
//        verify(unitRepository).findScoreById(1);
//    }



    @Test
    @DisplayName("deve atualizar MemberData com sucesso")
    void updateSuccessfully() {
        when(memberDataRepository.findByCpf(anyString())).thenReturn(Optional.of(memberData));
        when(unitRepository.findById(anyInt())).thenReturn(Optional.of(unit));
//        when(medicalDataService.update(anyString(), any())).thenReturn(medicalData);
        when(addressService.update(anyInt(), any())).thenReturn(address);
        when(memberDataRepository.save(any())).thenReturn(memberData);

        MemberData result = memberDataService.update("123", memberData);

        assertEquals(memberData, result);
        verify(memberDataRepository).findByCpf("123");
        verify(unitRepository).findById(1);
//        verify(medicalDataService).update("123", medicalData);
        verify(addressService).update(1, address);
        verify(memberDataRepository).save(memberData);
    }

    @Test
    @DisplayName("deve lançar exceção ao atualizar MemberData inexistente")
    void updateNotFound() {
        when(memberDataRepository.findByCpf(anyString())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> memberDataService.update("123", memberData));
        verify(memberDataRepository).findByCpf("123");
        verify(memberDataRepository, never()).save(any());
    }

    @Test
    @DisplayName("deve lançar exceção ao atualizar MemberData com Unit inexistente")
    void updateWithUnitNotFound() {
        when(memberDataRepository.findByCpf(anyString())).thenReturn(Optional.of(memberData));
        when(unitRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> memberDataService.update("123", memberData));
        verify(memberDataRepository, never()).save(any());
    }
}