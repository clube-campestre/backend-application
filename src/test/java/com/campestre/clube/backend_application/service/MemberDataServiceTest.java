package com.campestre.clube.backend_application.service;

import com.campestre.clube.backend_application.entity.*;
import com.campestre.clube.backend_application.entity.enums.ClassCategory;
import com.campestre.clube.backend_application.entity.enums.ClassRole;
import com.campestre.clube.backend_application.entity.enums.UnitRole;
import com.campestre.clube.backend_application.entity.models.MemberDataForClass;
import com.campestre.clube.backend_application.entity.models.MemberDataForUnit;
import com.campestre.clube.backend_application.entity.models.Pagination;
import com.campestre.clube.backend_application.exceptions.BadRequestException;
import com.campestre.clube.backend_application.exceptions.ConflictException;
import com.campestre.clube.backend_application.exceptions.InternalServerException;
import com.campestre.clube.backend_application.exceptions.NotFoundException;
import com.campestre.clube.backend_application.repository.MemberDataRepository;
import org.antlr.v4.runtime.misc.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

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
    private UnitService unitService;
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
        unit.setScore(10);
        unit.setSurname("Panda");

        address = new Address();
        address.setId(1);

        medicalData = new MedicalData();
        medicalData.setCpf("123");
        medicalData.setCns("cns");

        memberData = new MemberData();
        memberData.setCpf("123");
        memberData.setUnit(unit);
        memberData.setAddress(address);
        memberData.setMedicalData(medicalData);
        memberData.setIdImage("imgId");
        memberData.setImagePath("imgPath");
        memberData.setUsername("user");
    }

    @Test
    @DisplayName("deve registrar MemberData com sucesso")
    void registerSuccessfully() {
        when(memberDataRepository.existsByCpf("123")).thenReturn(false);
        when(medicalDataService.existsByCns("cns")).thenReturn(false);
        when(unitService.findByIdOrThrow(1)).thenReturn(unit);
        when(medicalDataService.save(medicalData)).thenReturn(medicalData);
        when(addressService.saveIfNotExist(address)).thenReturn(address);
        when(memberDataRepository.save(memberData)).thenReturn(memberData);

        MemberData result = memberDataService.register(memberData);

        assertEquals(memberData, result);
        verify(memberDataRepository).existsByCpf("123");
        verify(medicalDataService).existsByCns("cns");
        verify(unitService).findByIdOrThrow(1);
        verify(medicalDataService).save(medicalData);
        verify(addressService).saveIfNotExist(address);
        verify(memberDataRepository).save(memberData);
    }

    @Test
    @DisplayName("deve lançar ConflictException se CPF já existir")
    void registerWithExistingCpf() {
        when(memberDataRepository.existsByCpf("123")).thenReturn(true);

        assertThrows(ConflictException.class, () -> memberDataService.register(memberData));
        verify(memberDataRepository, never()).save(any());
    }

    @Test
    @DisplayName("deve lançar ConflictException se CNS já existir")
    void registerWithExistingCns() {
        when(memberDataRepository.existsByCpf("123")).thenReturn(false);
        when(medicalDataService.existsByCns("cns")).thenReturn(true);

        assertThrows(ConflictException.class, () -> memberDataService.register(memberData));
        verify(memberDataRepository, never()).save(any());
    }

    @Test
    @DisplayName("deve lançar NotFoundException se unidade não existir")
    void registerWithUnitNotFound() {
        when(memberDataRepository.existsByCpf("123")).thenReturn(false);
        when(medicalDataService.existsByCns("cns")).thenReturn(false);
        when(unitService.findByIdOrThrow(1)).thenThrow(new NotFoundException("not found"));

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
        when(memberDataRepository.findByCpf("123")).thenReturn(Optional.of(memberData));

        assertEquals(memberData, memberDataService.getById("123"));
        verify(memberDataRepository).findByCpf("123");
    }

    @Test
    @DisplayName("deve lançar NotFoundException ao buscar MemberData inexistente")
    void getByIdNotFound() {
        when(memberDataRepository.findByCpf("123")).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> memberDataService.getById("123"));
        verify(memberDataRepository).findByCpf("123");
    }

    @Test
    @DisplayName("deve atualizar MemberData com sucesso")
    void updateSuccessfully() {
        when(memberDataRepository.findByCpf("123")).thenReturn(Optional.of(memberData));
        when(unitService.findByIdOrThrow(1)).thenReturn(unit);
        when(medicalDataService.update(medicalData)).thenReturn(medicalData);
        when(addressService.update(1, address)).thenReturn(address);
        when(memberDataRepository.save(memberData)).thenReturn(memberData);

        MemberData result = memberDataService.update("123", memberData);

        assertEquals(memberData, result);
        verify(memberDataRepository).findByCpf("123");
        verify(unitService).findByIdOrThrow(1);
        verify(medicalDataService).update(medicalData);
        verify(addressService).update(1, address);
        verify(memberDataRepository).save(memberData);
    }

    @Test
    @DisplayName("deve lançar NotFoundException ao atualizar MemberData inexistente")
    void updateNotFound() {
        when(memberDataRepository.findByCpf("123")).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> memberDataService.update("123", memberData));
        verify(memberDataRepository).findByCpf("123");
        verify(memberDataRepository, never()).save(any());
    }

    @Test
    @DisplayName("deve lançar NotFoundException ao atualizar com unidade inexistente")
    void updateWithUnitNotFound() {
        when(memberDataRepository.findByCpf("123")).thenReturn(Optional.of(memberData));
        when(unitService.findByIdOrThrow(1)).thenThrow(new NotFoundException("not found"));

        assertThrows(NotFoundException.class, () -> memberDataService.update("123", memberData));
        verify(memberDataRepository, never()).save(any());
    }


    @Test
    @DisplayName("deve lançar NotFoundException ao deletar MemberData inexistente")
    void deleteNotFound() {
        when(memberDataRepository.findByCpf("123")).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> memberDataService.delete("123"));
        verify(memberDataRepository, never()).deleteById(any());
    }


    @Test
    @DisplayName("deve lançar BadRequestException se unidade não tem conselheiro")
    void getByUnitAndPaginationNoCounselor() {
        when(unitService.findByIdOrThrow(1)).thenReturn(unit);
        when(memberDataRepository.findByUnitIdAndUnitRole(1, UnitRole.CONSELHEIRO)).thenReturn(List.of());

        assertThrows(BadRequestException.class, () -> memberDataService.getByUnitAndPagination(1, 0, 10));
    }

    @Test
    @DisplayName("deve lançar BadRequestException se unidade tem mais de um conselheiro")
    void getByUnitAndPaginationMultipleCounselors() {
        when(unitService.findByIdOrThrow(1)).thenReturn(unit);
        MemberData c1 = new MemberData(), c2 = new MemberData();
        when(memberDataRepository.findByUnitIdAndUnitRole(1, UnitRole.CONSELHEIRO)).thenReturn(List.of(c1, c2));

        assertThrows(BadRequestException.class, () -> memberDataService.getByUnitAndPagination(1, 0, 10));
    }



    @Test
    @DisplayName("deve lançar BadRequestException se classe não tem instrutor")
    void getByClassAndPaginationNoInstructor() {
        when(memberDataRepository.findByClassCategoryAndClassRole(ClassCategory.AMIGO, ClassRole.INSTRUTOR))
                .thenReturn(List.of());

        assertThrows(BadRequestException.class, () -> memberDataService.getByClassAndPagination(ClassCategory.AMIGO, 0, 10));
    }

    @Test
    @DisplayName("deve lançar BadRequestException se classe tem mais de um instrutor")
    void getByClassAndPaginationMultipleInstructors() {
        MemberData i1 = new MemberData(), i2 = new MemberData();
        when(memberDataRepository.findByClassCategoryAndClassRole(ClassCategory.AMIGO, ClassRole.INSTRUTOR))
                .thenReturn(List.of(i1, i2));

        assertThrows(BadRequestException.class, () -> memberDataService.getByClassAndPagination(ClassCategory.AMIGO, 0, 10));
    }


}