package com.campestre.clube.backend_application.infrastructure.web.mappers;

import com.campestre.clube.backend_application.core.application.memberdata.command.SaveMemberDataCommand;
import com.campestre.clube.backend_application.core.application.memberdata.command.UpdateMemberDataCommand;
import com.campestre.clube.backend_application.core.application.valueobject.Pagination;
import com.campestre.clube.backend_application.core.domain.MemberData;
import com.campestre.clube.backend_application.core.domain.MemberDataForClass;
import com.campestre.clube.backend_application.core.domain.MemberDataForUnit;
import com.campestre.clube.backend_application.core.domain.enums.*;
import com.campestre.clube.backend_application.core.domain.valueobject.Image;
import com.campestre.clube.backend_application.infrastructure.persistence.jpa.unit.UnitEntityMapper;
import com.campestre.clube.backend_application.infrastructure.utils.MultipartConverter;
import com.campestre.clube.backend_application.infrastructure.web.dtos.memberdata.*;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

public class MemberDataDtoMapper {

    public static SaveMemberDataCommand toCommand(String jsonDto, MultipartFile file) {
        Image image = MultipartConverter.toImage(file);
        MemberDataRequestDto dto = MultipartConverter.fromJson(jsonDto, MemberDataRequestDto.class);
        return new SaveMemberDataCommand(
                dto.getCpf(),
                image.getValue(),
                image.getFormat(),
                dto.getUsername(),
                dto.getBirthDate(),
                Sex.fromString(dto.getSex()),
                dto.getBirthCertificate(),
                TshirtSize.fromString(dto.getTshirtSize()),
                dto.getBaptized(),
                dto.getContact(),
                dto.getIssuingAuthority(),

                dto.getUnitName(),
                UnitRole.fromString(dto.getUnitRole()),
                ClassCategory.fromString(dto.getClassCategory()),
                ClassRole.fromString(dto.getClassRole()),

                dto.getFatherName(),
                dto.getFatherContact(),
                dto.getFatherEmail(),
                dto.getMotherName(),
                dto.getMotherContact(),
                dto.getMotherEmail(),
                dto.getResponsibleName(),
                dto.getResponsibleContact(),
                dto.getResponsibleEmail(),

                dto.getAddress().getStreet(),
                dto.getAddress().getHouseNumber(),
                dto.getAddress().getDistrict(),
                dto.getAddress().getState(),
                dto.getAddress().getCity(),
                dto.getAddress().getCep(),
                dto.getAddress().getReferenceHouse(),

                dto.getMedicalData().getCns(),
                dto.getMedicalData().getAgreement(),
                dto.getMedicalData().getBloodType(),

                dto.getMedicalData().getCatapora(),
                dto.getMedicalData().getMeningite(),
                dto.getMedicalData().getHepatite(),
                dto.getMedicalData().getDengue(),
                dto.getMedicalData().getPneumonia(),
                dto.getMedicalData().getMalaria(),
                dto.getMedicalData().getFebreAmarela(),
                dto.getMedicalData().getSarampo(),
                dto.getMedicalData().getTetano(),
                dto.getMedicalData().getVariola(),
                dto.getMedicalData().getCoqueluche(),
                dto.getMedicalData().getDifteria(),
                dto.getMedicalData().getRinite(),
                dto.getMedicalData().getBronquite(),
                dto.getMedicalData().getAsma(),
                dto.getMedicalData().getRubeola(),
                dto.getMedicalData().getColera(),
                dto.getMedicalData().getCovid19(),
                dto.getMedicalData().getH1n1(),
                dto.getMedicalData().getCaxumba(),

                dto.getMedicalData().getOthers(),
                dto.getMedicalData().getHeartProblems(),
                dto.getMedicalData().getDrugAllergy(),
                dto.getMedicalData().getLactoseAllergy(),
                dto.getMedicalData().getDeficiency(),
                dto.getMedicalData().getBloodTransfusion(),
                dto.getMedicalData().getSkinAllergy(),
                dto.getMedicalData().getSkinAllergyMedications(),
                dto.getMedicalData().getFaintingOrConvulsion(),
                dto.getMedicalData().getFaintingOrSeizuresMedications(),
                dto.getMedicalData().getPsychologicalDisorder(),
                dto.getMedicalData().getAllergy(),
                dto.getMedicalData().getAllergyMedications(),
                dto.getMedicalData().getDiabetic(),
                dto.getMedicalData().getDiabeticMedications(),
                dto.getMedicalData().getRecentSeriousInjury(),
                dto.getMedicalData().getRecentFracture(),
                dto.getMedicalData().getSurgeries(),
                dto.getMedicalData().getHospitalizationReasonLast5Years()
        );
    }

    public static UpdateMemberDataCommand toCommand(String jsonDto, String cpf, MultipartFile file) {
        Image image = MultipartConverter.toImage(file);
        MemberDataRequestDto dto = MultipartConverter.fromJson(jsonDto, MemberDataRequestDto.class);
        return new UpdateMemberDataCommand(
                cpf,
                image.getValue(),
                image.getFormat(),
                dto.getUsername(),
                dto.getBirthDate(),
                Sex.fromString(dto.getSex()),
                dto.getBirthCertificate(),
                TshirtSize.fromString(dto.getTshirtSize()),
                dto.getBaptized(),
                dto.getContact(),
                dto.getIssuingAuthority(),

                dto.getUnitName(),
                UnitRole.fromString(dto.getUnitRole()),
                ClassCategory.fromString(dto.getClassCategory()),
                ClassRole.fromString(dto.getClassRole()),

                dto.getFatherName(),
                dto.getFatherContact(),
                dto.getFatherEmail(),
                dto.getMotherName(),
                dto.getMotherContact(),
                dto.getMotherEmail(),
                dto.getResponsibleName(),
                dto.getResponsibleContact(),
                dto.getResponsibleEmail(),

                dto.getAddress().getStreet(),
                dto.getAddress().getHouseNumber(),
                dto.getAddress().getDistrict(),
                dto.getAddress().getState(),
                dto.getAddress().getCity(),
                dto.getAddress().getCep(),
                dto.getAddress().getReferenceHouse(),

                dto.getMedicalData().getCns(),
                dto.getMedicalData().getAgreement(),
                dto.getMedicalData().getBloodType(),

                dto.getMedicalData().getCatapora(),
                dto.getMedicalData().getMeningite(),
                dto.getMedicalData().getHepatite(),
                dto.getMedicalData().getDengue(),
                dto.getMedicalData().getPneumonia(),
                dto.getMedicalData().getMalaria(),
                dto.getMedicalData().getFebreAmarela(),
                dto.getMedicalData().getSarampo(),
                dto.getMedicalData().getTetano(),
                dto.getMedicalData().getVariola(),
                dto.getMedicalData().getCoqueluche(),
                dto.getMedicalData().getDifteria(),
                dto.getMedicalData().getRinite(),
                dto.getMedicalData().getBronquite(),
                dto.getMedicalData().getAsma(),
                dto.getMedicalData().getRubeola(),
                dto.getMedicalData().getColera(),
                dto.getMedicalData().getCovid19(),
                dto.getMedicalData().getH1n1(),
                dto.getMedicalData().getCaxumba(),

                dto.getMedicalData().getOthers(),
                dto.getMedicalData().getHeartProblems(),
                dto.getMedicalData().getDrugAllergy(),
                dto.getMedicalData().getLactoseAllergy(),
                dto.getMedicalData().getDeficiency(),
                dto.getMedicalData().getBloodTransfusion(),
                dto.getMedicalData().getSkinAllergy(),
                dto.getMedicalData().getSkinAllergyMedications(),
                dto.getMedicalData().getFaintingOrConvulsion(),
                dto.getMedicalData().getFaintingOrSeizuresMedications(),
                dto.getMedicalData().getPsychologicalDisorder(),
                dto.getMedicalData().getAllergy(),
                dto.getMedicalData().getAllergyMedications(),
                dto.getMedicalData().getDiabetic(),
                dto.getMedicalData().getDiabeticMedications(),
                dto.getMedicalData().getRecentSeriousInjury(),
                dto.getMedicalData().getRecentFracture(),
                dto.getMedicalData().getSurgeries(),
                dto.getMedicalData().getHospitalizationReasonLast5Years()
        );
    }

    public static MemberDataResponseDto toResponse(MemberData domain) {
        return new MemberDataResponseDto(
                domain.getCpf().getNumber(),
                domain.getImage().getValue(),
                domain.getImage().getFormat(),
                domain.getUsername(),
                domain.getBirthDate(),
                domain.getSex(),
                domain.getBirthCertificate(),
                domain.getTshirtSize(),
                domain.getBaptized(),
                domain.getCellphoneNumber().getNumber(),
                domain.getIssuingAuthority(),

                UnitEntityMapper.toResponse(domain.getUnit()),
                domain.getUnitRole(),
                domain.getClassCategory(),
                domain.getClassRole(),

                domain.getFatherContact().getName(),
                domain.getFatherContact().getCellphoneNumber().getNumber(),
                domain.getFatherContact().getEmail().getValue(),
                domain.getMotherContact().getName(),
                domain.getMotherContact().getCellphoneNumber().getNumber(),
                domain.getMotherContact().getEmail().getValue(),
                domain.getResponsibleContact().getName(),
                domain.getResponsibleContact().getCellphoneNumber().getNumber(),
                domain.getResponsibleContact().getEmail().getValue(),

                AddressDtoMapper.toResponse(domain.getAddress()),
                MedicalDataDtoMapper.toResponse(domain.getMedicalData())
        );
    }

    public static MemberDataForUnitResponseDto toResponse(MemberDataForUnit domain) {
        return new MemberDataForUnitResponseDto(
                domain.getScore(),
                domain.getCounselorName(),
                domain.getPageNumber(),
                domain.getPageSize(),
                domain.getTotalItems(),
                domain.getTotalPages(),
                domain.getMembers().stream().map(MemberDataDtoMapper::toResponse).collect(Collectors.toList())
        );
    }

    public static MemberDataForClassResponseDto toResponse(MemberDataForClass domain) {
        return new MemberDataForClassResponseDto(
                domain.getInstructorName(),
                domain.getPageNumber(),
                domain.getPageSize(),
                domain.getTotalItems(),
                domain.getTotalPages(),
                domain.getMembers().stream().map(MemberDataDtoMapper::toResponse).collect(Collectors.toList())
        );
    }

    public static GetByFilterAndPaginationMemberDataResponseDto toResponse(Pair<List<MemberData>, Pagination> value) {
        return new GetByFilterAndPaginationMemberDataResponseDto(
                value.b.getPageNumber(),
                value.b.getPageSize(),
                value.b.getTotalItems(),
                value.b.getTotalPages(),
                MemberDataDtoMapper.toResponse(value.a)
        );
    }

    public static List<MemberDataResponseDto> toResponse(List<MemberData> domains) {
        return domains.stream().map(MemberDataDtoMapper::toResponse).collect(Collectors.toList());
    }
}