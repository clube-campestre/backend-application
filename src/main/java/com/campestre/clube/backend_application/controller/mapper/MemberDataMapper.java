package com.campestre.clube.backend_application.controller.mapper;

import com.campestre.clube.backend_application.controller.dtos.requests.MemberDataDtoRequest;
import com.campestre.clube.backend_application.controller.dtos.responses.MemberDataDtoResponse;
import com.campestre.clube.backend_application.entity.Address;
import com.campestre.clube.backend_application.entity.MedicalData;
import com.campestre.clube.backend_application.entity.MemberData;
import com.campestre.clube.backend_application.entity.Unit;
import org.springframework.stereotype.Component;

@Component
public class MemberDataMapper {

    public static MemberData toEntity(MemberDataDtoRequest dto, Unit unit, MedicalData medicalData, Address address) {
        MemberData member = new MemberData();
        member.setCpf(dto.getCpf());
        member.setUsername(dto.getUsername());
        member.setBirthDate(dto.getBirthDate());
        member.setSex(dto.getSex());
        member.setBirthCertificate(dto.getBirthCertificate());
        member.setTshirtSize(dto.getTshirtSize());
        member.setIsBaptized(dto.getIsBaptized());
        member.setContact(dto.getContact());
        member.setUnit(unit);
        member.setClassCategory(dto.getClassCategory());
        member.setFatherName(dto.getFatherName());
        member.setFatherContact(dto.getFatherContact());
        member.setFatherEmail(dto.getFatherEmail());
        member.setMotherName(dto.getMotherName());
        member.setMotherContact(dto.getMotherContact());
        member.setMotherEmail(dto.getMotherEmail());
        member.setResponsibleName(dto.getResponsibleName());
        member.setResponsibleContact(dto.getResponsibleContact());
        member.setResponsibleEmail(dto.getResponsibleEmail());
        member.setAddress(address);
        member.setMedicalData(medicalData);
        return member;
    }

    public static MemberDataDtoResponse toResponse(MemberData member) {
        MemberDataDtoResponse dto = new MemberDataDtoResponse();
        dto.setCpf(member.getCpf());
        dto.setUsername(member.getUsername());
        dto.setBirthDate(member.getBirthDate());
        dto.setSex(member.getSex());
        dto.setBirthCertificate(member.getBirthCertificate());
        dto.setTshirtSize(member.getTshirtSize());
        dto.setIsBaptized(member.getIsBaptized());
        dto.setContact(member.getContact());
        dto.setUnitId(member.getUnit().getId());
        dto.setClassCategory(member.getClassCategory());
        dto.setFatherName(member.getFatherName());
        dto.setFatherContact(member.getFatherContact());
        dto.setFatherEmail(member.getFatherEmail());
        dto.setMotherName(member.getMotherName());
        dto.setMotherContact(member.getMotherContact());
        dto.setMotherEmail(member.getMotherEmail());
        dto.setResponsibleName(member.getResponsibleName());
        dto.setResponsibleContact(member.getResponsibleContact());
        dto.setResponsibleEmail(member.getResponsibleEmail());
        dto.setAddressId(member.getAddress().getId());
        dto.setMedicalDataId(member.getMedicalData().getCpf());
        dto.setImagePath(member.getImagePath());
        dto.setIdImage(member.getIdImage());
        return dto;
    }
}
