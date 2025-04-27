package com.campestre.clube.backend_application.service;

import com.campestre.clube.backend_application.controller.dtos.requests.MemberDataDtoRequest;
import com.campestre.clube.backend_application.controller.mapper.MemberDataMapper;
import com.campestre.clube.backend_application.entity.Address;
import com.campestre.clube.backend_application.entity.MedicalData;
import com.campestre.clube.backend_application.entity.MemberData;
import com.campestre.clube.backend_application.entity.Unit;
import com.campestre.clube.backend_application.entity.enums.ClassCategory;
import com.campestre.clube.backend_application.exceptions.ConflictException;
import com.campestre.clube.backend_application.exceptions.NotFoundException;
import com.campestre.clube.backend_application.repository.MemberDataRepository;
import com.campestre.clube.backend_application.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
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

    public MemberData register(MemberDataDtoRequest memberDto) {
        Unit unit = findUnitOrThrow(memberDto.getUnitId());

        MedicalData medicalData = medicalDataService.createMedicalData(memberDto.getMedicalData());
        Address address = addressService.createAddress(memberDto.getAddress());

        if (memberDataRepository.existsByCpf(memberDto.getCpf())) {
            throw new ConflictException("Member with existing CPF [%s] or Medical Data".formatted(memberDto.getCpf()));
        }

        if(!addressService.addressAlreadyExists(address.getCep())) addressService.register(address);
        medicalDataService.register(medicalData);
        MemberData member = MemberDataMapper.toEntity(memberDto, unit, medicalData, address);
        memberDataRepository.save(member);

        return member;
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

    public MemberData update(String cpf, MemberDataDtoRequest dto) {
        validateMemberExists(cpf);

        Unit unit = findUnitOrThrow(dto.getUnitId());

        // Atualizar ou reutilizar dados médicos
        MedicalData updatedMedicalData = medicalDataService.update(cpf, dto.getMedicalData());
        // Atualizar ou reutilizar endereço
        Address updatedAddress = addressService.update( dto.getAddressId(), dto.getAddress());

        // Salva tudo
        medicalDataService.register(updatedMedicalData);
        addressService.register(updatedAddress);

        dto.setCpf(cpf);
        MemberData updateMember = MemberDataMapper.toEntity(dto, unit, updatedMedicalData, updatedAddress);
        memberDataRepository.save(updateMember);

        return updateMember;
    }


    public void delete(String cpf) throws GeneralSecurityException, IOException {
        MemberData memberToDelete = validateMemberExists(cpf);

        memberDataRepository.deleteById(cpf);
        medicalDataService.delete(cpf);
        driveService.deleteFile(memberToDelete.getIdImage());
        addressService.delete(memberToDelete.getAddress().getId());
    }


    private MemberData validateMemberExists(String cpf) {
        return memberDataRepository.findByCpf(cpf)
                .orElseThrow(() -> new NotFoundException("Member by cpf [%s] not found".formatted(cpf)));
    }
}
