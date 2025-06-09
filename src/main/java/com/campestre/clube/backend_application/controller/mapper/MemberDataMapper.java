package com.campestre.clube.backend_application.controller.mapper;

import com.campestre.clube.backend_application.controller.dtos.requests.MemberDataDtoRequest;
import com.campestre.clube.backend_application.controller.dtos.responses.*;
import com.campestre.clube.backend_application.entity.MemberData;
import com.campestre.clube.backend_application.entity.enums.*;
import com.campestre.clube.backend_application.entity.models.MemberDataForClass;
import com.campestre.clube.backend_application.entity.models.MemberDataForUnit;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MemberDataMapper {

    public static MemberData toEntity(MemberDataDtoRequest dto) {
        return new MemberData(
                dto.getCpf(), dto.getIdImage(), dto.getImagePath(), dto.getUsername(), dto.getBirthDate(),
                Sex.fromString(dto.getSex()), dto.getBirthCertificate(), TshirtSize.fromString(dto.getTshirtSize()),
                dto.getIsBaptized(), dto.getContact(), dto.getIssuingAuthority(), UnitMapper.toEntity(dto.getUnit()),
                UnitRole.fromString(dto.getUnitRole()), ClassCategory.fromString(dto.getClassCategory()),
                ClassRole.fromString(dto.getClassRole()), dto.getFatherName(), dto.getFatherContact(),
                dto.getFatherEmail(), dto.getMotherName(), dto.getMotherContact(), dto.getMotherEmail(),
                dto.getResponsibleName(), dto.getResponsibleContact(), dto.getResponsibleEmail(),
                AddressMapper.toEntity(dto.getAddress()), MedicalDataMapper.toEntity(dto.getMedicalData())
        );
    }

    public static MemberDataResponseDto toResponse(MemberData member) {
        return new MemberDataResponseDto(
                member.getCpf(), member.getIdImage(), member.getImagePath(), member.getUsername(),
                member.getBirthDate(), member.getSex(), member.getBirthCertificate(), member.getTshirtSize(),
                member.getIsBaptized(), member.getContact(), member.getIssuingAuthority(),
                UnitMapper.toResponse(member.getUnit()), member.getUnitRole(), member.getClassCategory(),
                member.getClassRole(), member.getFatherName(), member.getFatherContact(), member.getFatherEmail(),
                member.getMotherName(), member.getMotherContact(), member.getMotherEmail(), member.getResponsibleName(),
                member.getResponsibleContact(), member.getResponsibleEmail(),
                AddressMapper.toResponse(member.getAddress()), MedicalDataMapper.toResponse(member.getMedicalData())
        );
    }

    public static MemberDataForUnitDtoResponse toResponse(MemberDataForUnit memberDataForUnit) {
        return new MemberDataForUnitDtoResponse(
                memberDataForUnit.getScore(),
                memberDataForUnit.getCounselorName(),
                memberDataForUnit.getPagination().getPageNumber(),
                memberDataForUnit.getPagination().getPageSize(),
                memberDataForUnit.getPagination().getTotalItems(),
                memberDataForUnit.getPagination().getTotalPages(),
                MemberDataMapper.toResponse(memberDataForUnit.getMembers())
        );
    }

    public static MemberDataForClassDtoResponse toResponse(MemberDataForClass memberDataForClass) {
        return new MemberDataForClassDtoResponse(
                memberDataForClass.getInstructorName(),
                memberDataForClass.getPagination().getPageNumber(),
                memberDataForClass.getPagination().getPageSize(),
                memberDataForClass.getPagination().getTotalItems(),
                memberDataForClass.getPagination().getTotalPages(),
                MemberDataMapper.toResponse(memberDataForClass.getMembers())
        );
    }

    public static List<MemberDataResponseDto> toResponse(List<MemberData> memberData){
        return memberData.stream().map(MemberDataMapper::toResponse).toList();
    }

    public static GetByFilterAndPaginationMemberDataResponseDto toResponse(
            List<MemberData> memberData, Integer pageNumber, Integer pageSize, Long totalItems, Integer totalPages
    ){
        return new GetByFilterAndPaginationMemberDataResponseDto(
                pageNumber, pageSize, totalItems, totalPages, MemberDataMapper.toResponse(memberData)
        );
    }
}
