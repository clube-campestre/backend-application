package com.campestre.clube.backend_application.service;

import com.campestre.clube.backend_application.entity.*;
import com.campestre.clube.backend_application.entity.enums.ClassCategory;
import com.campestre.clube.backend_application.entity.enums.ClassRole;
import com.campestre.clube.backend_application.entity.enums.UnitEnum;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberDataService {

    @Autowired
    private MemberDataRepository memberDataRepository;
    @Autowired
    private UnitService unitService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private MedicalDataService medicalDataService;
    @Autowired
    private DriveService driveService;

    public MemberData register(MemberData memberData) {
        if(memberDataRepository.existsByCpf(memberData.getCpf()))
            throw new NotFoundException("Member by cpf [%s] already exists".formatted(memberData.getCpf()));
        if (medicalDataService.existsByCns(memberData.getMedicalData().getCns()))
            throw new ConflictException(
                    "Medical data with this CNS [%s] already exists".formatted(memberData.getMedicalData().getCns())
            );

        Unit unit = unitService.findByIdOrThrow(memberData.getUnit().getId());

        memberData.getMedicalData().setCpf(memberData.getCpf());
        MedicalData medicalData = medicalDataService.save(memberData.getMedicalData());
        Address address = addressService.saveIfNotExist(memberData.getAddress());

        memberData.setUnit(unit);
        memberData.setAddress(address);
        memberData.setMedicalData(medicalData);
        return memberDataRepository.save(memberData);
    }


    public List<MemberData> getAll() {
        return memberDataRepository.findAll();
    }

    public MemberData getById(String cpf) {
        return findOrThrow(cpf);
    }

    public MemberDataForUnit getByUnitAndPagination(Integer unitId, Integer page, Integer size) {
        Unit unit = unitService.findByIdOrThrow(unitId);

        List<MemberData> counselors = memberDataRepository.findByUnitIdAndUnitRole(unitId, UnitRole.CONSELHEIRO);
        if (counselors.isEmpty())
            throw new BadRequestException(
                    "The unit with id [%s] should have at least 1 counselor".formatted(unitId)
            );
        if (counselors.size() > 1)
            throw new BadRequestException(
                    "The unit with id [%s] should not have more than one counselor".formatted(unitId)
            );

        Page<MemberData> result = memberDataRepository
                .findByUnitAndPagination(unitId, PageRequest.of(page, size));

        return new MemberDataForUnit(
                unit.getScore(), counselors.getFirst().getUsername(), createPagination(result), result.getContent()
        );
    }

    public MemberDataForClass getByClassAndPagination(ClassCategory classCategory, Integer page, Integer size) {
        List<MemberData> instructors = memberDataRepository
                .findByClassCategoryAndClassRole(classCategory, ClassRole.INSTRUTOR);
        if (instructors.isEmpty())
            throw new BadRequestException(
                    "The [%s] class should have at least 1 instructor".formatted(classCategory.name())
            );
        if (instructors.size() > 1)
            throw new BadRequestException(
                    "The [%s] class should not have more than one instructor".formatted(classCategory.name())
            );

        Page<MemberData> result = memberDataRepository
                .findByClassAndPagination(classCategory, PageRequest.of(page, size));

        return new MemberDataForClass(
                instructors.getFirst().getUsername(), createPagination(result), result.getContent()
        );
    }

    public MemberData update(String cpf, MemberData memberData) {
        findOrThrow(cpf);

        Unit unit = unitService.findByIdOrThrow(memberData.getUnit().getId());

        MedicalData updatedMedicalData =
                medicalDataService.update(memberData.getMedicalData().getCpf(), memberData.getMedicalData());
        Address updatedAddress = addressService.update(memberData.getAddress().getId(), memberData.getAddress());

        memberData.getMedicalData().setCpf(cpf);
        memberData.setCpf(cpf);
        memberData.setUnit(unit);
        memberData.setAddress(updatedAddress);
        memberData.setMedicalData(updatedMedicalData);
        return memberDataRepository.save(memberData);
    }


    public void delete(String cpf) {
        MemberData memberToDelete = findOrThrow(cpf);

        try {
            driveService.deleteFile(memberToDelete.getIdImage());
        } catch (Exception e) {
            throw new InternalServerException("Error deleting profile image of member with CPF [%s]".formatted(cpf));
        }
        memberDataRepository.deleteById(cpf);
        medicalDataService.delete(cpf);
        addressService.delete(memberToDelete.getAddress().getId());
    }

    public Pair<List<MemberData>, Pagination> getByFilterAndPagination(
            String unit, String classCategory, String name, Integer page, Integer size
    ) {
        UnitEnum unitEnum = unit != null ? UnitEnum.fromString(unit) : null;
        Unit unitEntity = unit != null ? unitService.findByIdOrThrow(unitEnum.getId()) : null;
        ClassCategory classCategoryEnum = classCategory != null ? ClassCategory.fromString(classCategory) : null;

        Page<MemberData> result = memberDataRepository.findByFilterAndPagination(
                unitEntity, classCategoryEnum, name, PageRequest.of(page, size)
        );
        return new Pair<>(result.getContent(), createPagination(result));
    }

    private <T> Pagination createPagination(Page<T> result) {
        return new Pagination(result.getNumber(), result.getSize(), result.getTotalElements(), result.getTotalPages());
    }

    private MemberData findOrThrow(String cpf) {
        return memberDataRepository.findByCpf(cpf)
                .orElseThrow(() -> new NotFoundException("Member by cpf [%s] not found".formatted(cpf)));
    }
}
