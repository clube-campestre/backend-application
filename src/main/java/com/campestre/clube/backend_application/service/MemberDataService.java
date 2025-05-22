package com.campestre.clube.backend_application.service;

import com.campestre.clube.backend_application.entity.Address;
import com.campestre.clube.backend_application.entity.MedicalData;
import com.campestre.clube.backend_application.entity.MemberData;
import com.campestre.clube.backend_application.entity.Unit;
import com.campestre.clube.backend_application.entity.enums.ClassCategory;
import com.campestre.clube.backend_application.entity.enums.ClassRole;
import com.campestre.clube.backend_application.entity.enums.UnitRole;
import com.campestre.clube.backend_application.exceptions.BadRequestException;
import com.campestre.clube.backend_application.exceptions.ConflictException;
import com.campestre.clube.backend_application.exceptions.InternalServerException;
import com.campestre.clube.backend_application.exceptions.NotFoundException;
import com.campestre.clube.backend_application.repository.MemberDataRepository;
import com.campestre.clube.backend_application.repository.UnitRepository;
import org.antlr.v4.runtime.misc.Pair;
import org.antlr.v4.runtime.misc.Triple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberDataService {

    @Autowired
    private MemberDataRepository memberDataRepository;
    @Autowired
    private UnitRepository unitRepository;
    @Autowired
    private AddressService addressService;
    @Autowired
    private MedicalDataService medicalDataService;
    @Autowired
    private DriveService driveService;

    public MemberData register(MemberData memberData) {
        Unit unit = findUnitOrThrow(memberData.getUnit().getId());

        MedicalData medicalData = medicalDataService.save(memberData.getMedicalData());
        Address address = addressService.saveIfNotExist(memberData.getAddress());

        if (memberDataRepository.existsByCpf(memberData.getCpf()))
            throw new ConflictException("Member with existing CPF [%s] or Medical Data".formatted(memberData.getCpf()));

        memberData.setUnit(unit);
        memberData.setAddress(address);
        memberData.setMedicalData(medicalData);
        return memberDataRepository.save(memberData);
    }

    private Unit findUnitOrThrow(Integer unitId) {
        return unitRepository.findById(unitId)
                .orElseThrow(() -> new NotFoundException("Unit by id [%s] not found".formatted(unitId)));
    }

    public List<MemberData> getAll() {
        return memberDataRepository.findAll();
    }

    public MemberData getById(String cpf) {
        return validateMemberExists(cpf);
    }

    public Triple<List<MemberData>, Integer, String> getByUnit(Integer unitId) {
        Optional<Unit> unit = unitRepository.findById(unitId);
        if (unit.isEmpty()) throw new NotFoundException("Unit by id [%s] not found".formatted(unitId));

        List<MemberData> counselors = memberDataRepository.findByUnitIdAndUnitRole(unitId, UnitRole.CONSELHEIRO);
        if (counselors.isEmpty()) throw new BadRequestException("The unit with id [%s] should have at least 1 counselor".formatted(unitId));
        if (counselors.size() > 1) throw new BadRequestException("The unit with id [%s] should not have more than one counselor".formatted(unitId));

        return new Triple<>(
                memberDataRepository.findByUnitIdAndUnitRoleNot(unitId, UnitRole.CONSELHEIRO),
                unit.get().getId(),
                counselors.getFirst().getUsername()
        );
    }

    public Pair<List<MemberData>, String> getByClass(ClassCategory classCategory) {
        List<MemberData> instructors = memberDataRepository.findByClassCategoryAndClassRole(classCategory, ClassRole.INSTRUTOR);
        if (instructors.isEmpty()) throw new BadRequestException("The [%s] class should have at least 1 instructor".formatted(classCategory.name()));
        if (instructors.size() > 1) throw new BadRequestException("The [%s] class should not have more than one instructor".formatted(classCategory.name()));
        return new Pair<List<MemberData>, String>(
                memberDataRepository.findByClassCategoryAndClassRoleNot(classCategory, ClassRole.INSTRUTOR),
                instructors.getFirst().getUsername()
        );
    }

    public MemberData update(String cpf, MemberData memberData) {
        validateMemberExists(cpf);

        Unit unit = findUnitOrThrow(memberData.getUnit().getId());

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
        MemberData memberToDelete = validateMemberExists(cpf);

        try {
            driveService.deleteFile(memberToDelete.getIdImage());
        } catch (Exception e) {
            throw new InternalServerException("Error deleting profile image of member with CPF [%s]".formatted(cpf));
        }
        memberDataRepository.deleteById(cpf);
        medicalDataService.delete(cpf);
        addressService.delete(memberToDelete.getAddress().getId());
    }


    private MemberData validateMemberExists(String cpf) {
        return memberDataRepository.findByCpf(cpf)
                .orElseThrow(() -> new NotFoundException("Member by cpf [%s] not found".formatted(cpf)));
    }
}
