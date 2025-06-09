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
            throw new ConflictException("Já existe um membro cadastrado com o CPF informado.");
        if (medicalDataService.existsByCns(memberData.getMedicalData().getCns()))
            throw new ConflictException("Já existe um membro cadastrado com CNS informado.");

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
                    "A unidade %s deve ter pelo menos um conselheiro.".formatted(unit.getSurname())
            );
        if (counselors.size() > 1)
            throw new BadRequestException(
                    "A unidade %s não pode ter mais de um conselheiro.".formatted(unit.getSurname())
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
                    "A classe %s deve ter pelo menos um instrutor.".formatted(classCategory.name())
            );
        if (instructors.size() > 1)
            throw new BadRequestException(
                    "A classe %s não pode ter mais de um instrutor.".formatted(classCategory.name())
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

        memberData.getMedicalData().setCpf(cpf);
        MedicalData updatedMedicalData = medicalDataService.update(memberData.getMedicalData());
        Address updatedAddress = addressService.update(memberData.getAddress().getId(), memberData.getAddress());

        memberData.setCpf(cpf);
        memberData.setUnit(unit);
        memberData.setAddress(updatedAddress);
        memberData.setMedicalData(updatedMedicalData);
        return memberDataRepository.save(memberData);
    }


    public void delete(String cpf) {
        MemberData memberToDelete = findOrThrow(cpf);

        if (memberToDelete.getIdImage() != null && memberToDelete.getImagePath() != null) {
            try {
                driveService.deleteFile(memberToDelete.getIdImage());
            } catch (Exception e) {
                throw new InternalServerException("Não foi possível excluir a foto de perfil. Tente novamente mais tarde.");
            }
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
                .orElseThrow(() -> new NotFoundException("Não encontramos um usuário com o CPF informado."));
    }
}
