package com.campestre.clube.backend_application.controller.mapper;

import com.campestre.clube.backend_application.controller.dtos.requests.MemberDataDtoRequest;
import com.campestre.clube.backend_application.controller.dtos.responses.MemberDataResponseDto;
import com.campestre.clube.backend_application.controller.dtos.responses.MemberDataForClassDtoResponse;
import com.campestre.clube.backend_application.controller.dtos.responses.MemberDataForUnitDtoResponse;
import com.campestre.clube.backend_application.entity.MemberData;
import com.campestre.clube.backend_application.entity.enums.ClassCategory;
import com.campestre.clube.backend_application.entity.enums.ClassRole;
import com.campestre.clube.backend_application.entity.enums.Sex;
import com.campestre.clube.backend_application.entity.enums.TshirtSize;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MemberDataMapper {

    public static MemberData toEntity(MemberDataDtoRequest dto) {
        MemberData member = new MemberData();
        member.setCpf(dto.getCpf());
        member.setUsername(dto.getUsername());
        member.setBirthDate(dto.getBirthDate());
        member.setSex(Sex.fromString(dto.getSex()));
        member.setBirthCertificate(dto.getBirthCertificate());
        member.setTshirtSize(TshirtSize.fromString(dto.getTshirtSize()));
        member.setIsBaptized(dto.getBaptized());
        member.setContact(dto.getContact());
        member.setUnit(UnitMapper.toEntity(dto.getUnit()));
        member.setClassRole(ClassRole.fromString(dto.getClassRole()));
        member.setClassCategory(ClassCategory.fromString(dto.getClassCategory()));
        member.setFatherName(dto.getFatherName());
        member.setFatherContact(dto.getFatherContact());
        member.setFatherEmail(dto.getFatherEmail());
        member.setMotherName(dto.getMotherName());
        member.setMotherContact(dto.getMotherContact());
        member.setMotherEmail(dto.getMotherEmail());
        member.setResponsibleName(dto.getResponsibleName());
        member.setResponsibleContact(dto.getResponsibleContact());
        member.setResponsibleEmail(dto.getResponsibleEmail());
        member.setAddress(AddressMapper.toEntity(dto.getAddress()));
        member.setMedicalData(MedicalDataMapper.toEntity(dto.getMedicalData()));
        member.setIssuingAuthority(dto.getIssuingAuthority());
        return member;
    }

    public static MemberDataResponseDto toResponse(MemberData member) {
        MemberDataResponseDto dto = new MemberDataResponseDto();
        dto.setCpf(member.getCpf());
        dto.setUsername(member.getUsername());
        dto.setBirthDate(member.getBirthDate());
        dto.setSex(member.getSex());
        dto.setBirthCertificate(member.getBirthCertificate());
        dto.setTshirtSize(member.getTshirtSize());
        dto.setBaptized(member.getBaptized());
        dto.setContact(member.getContact());
        dto.setUnit(UnitMapper.toResponse(member.getUnit()));
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
        dto.setImagePath(member.getImagePath());
        dto.setIdImage(member.getIdImage());
        dto.setAddress(AddressMapper.toResponse(member.getAddress()));
        dto.setMedicalData(MedicalDataMapper.toResponse(member.getMedicalData()));
        dto.setIssuingAuthority(member.getIssuingAuthority());
        return dto;
    }

    public static MemberDataForUnitDtoResponse toResponse(List<MemberData> members, Integer score, String counselorName) {
        MemberDataForUnitDtoResponse dto = new MemberDataForUnitDtoResponse();
        dto.setMembers(members.stream().map(MemberDataMapper::toResponse).toList());
        dto.setScore(score);
        dto.setCounselorName(counselorName);
        return dto;
    }

    public static MemberDataForClassDtoResponse toResponse(List<MemberData> members, String instructorName) {
        MemberDataForClassDtoResponse dto = new MemberDataForClassDtoResponse();
        dto.setMembers(members.stream().map(MemberDataMapper::toResponse).toList());
        dto.setInstructorName(instructorName);
        return dto;
    }
}
