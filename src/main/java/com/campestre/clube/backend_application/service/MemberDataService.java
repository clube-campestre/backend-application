package com.campestre.clube.backend_application.service;

import com.campestre.clube.backend_application.entity.Address;
import com.campestre.clube.backend_application.entity.MedicalData;
import com.campestre.clube.backend_application.entity.MemberData;
import com.campestre.clube.backend_application.entity.Unit;
import com.campestre.clube.backend_application.entity.enums.ClassCategory;
import com.campestre.clube.backend_application.exceptions.ConflictException;
import com.campestre.clube.backend_application.exceptions.InternalServerException;
import com.campestre.clube.backend_application.exceptions.NotFoundException;
import com.campestre.clube.backend_application.repository.MemberDataRepository;
import com.campestre.clube.backend_application.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

        MedicalData medicalData = medicalDataService.createMedicalData(memberData.getMedicalData());
        Address address = addressService.create(memberData.getAddress());

        if (memberDataRepository.existsByCpf(memberData.getCpf())) {
            throw new ConflictException("Member with existing CPF [%s] or Medical Data".formatted(memberData.getCpf()));
        }

        medicalDataService.register(medicalData);
        memberData.setUnit(unit);
        memberData.setAddress(address);
        memberData.setMedicalData(medicalData);
        return memberDataRepository.save(memberData);
    }

    private Unit findUnitOrThrow(Integer unitId) {
        return unitRepository.findById(unitId)
                .orElseThrow(() -> new NotFoundException("Unit by id [%s] not found".formatted(unitId)));
    }

    public List<MemberData> getAll() {return memberDataRepository.findAll();}

    public MemberData getById(String cpf) { return validateMemberExists(cpf);}

    public List<MemberData> getByUnit(Integer unitId) { return  memberDataRepository.findAllByUnitId(unitId);}

    public List<MemberData> getByClass(ClassCategory classCategory) {
        return memberDataRepository.findByClassCategory(classCategory);
    }

    public MemberData update(String cpf, MemberData memberData) {
        validateMemberExists(cpf);

        Unit unit = findUnitOrThrow(memberData.getUnit().getId());

        // Atualizar ou reutilizar dados médicos
        MedicalData updatedMedicalData = medicalDataService.update(cpf, memberData.getMedicalData());
        // Atualizar ou reutilizar endereço
        Address updatedAddress = addressService.update( memberData.getAddress().getId(), memberData.getAddress());

        // Salva tudo
        medicalDataService.register(updatedMedicalData);
        addressService.register(updatedAddress);

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
