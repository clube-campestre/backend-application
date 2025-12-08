package com.campestre.clube.backend_application.infrastructure.persistence.jpa.memberdata;

import com.campestre.clube.backend_application.core.domain.MemberData;
import com.campestre.clube.backend_application.core.domain.valueobject.MemberContact;
import com.campestre.clube.backend_application.infrastructure.persistence.jpa.address.AddressEntityMapper;
import com.campestre.clube.backend_application.infrastructure.persistence.jpa.medicaldata.MedicalDataEntityMapper;
import com.campestre.clube.backend_application.infrastructure.persistence.jpa.unit.UnitEntityMapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class MemberDataEntityMapper {
    public static MemberDataEntity toEntity(MemberData domain) {
        if (domain == null) return null;
        MemberDataEntity entity = new MemberDataEntity();
        entity.setCpf(domain.getCpf().getNumber());
        entity.setImage(domain.getImage().getValue());
        entity.setImageFormat(domain.getImage().getFormat());
        entity.setUsername(domain.getUsername());
        entity.setBirthDate(LocalDate.parse(domain.getBirthDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        entity.setSex(domain.getSex());
        entity.setBirthCertificate(domain.getBirthCertificate());
        entity.setTshirtSize(domain.getTshirtSize());
        entity.setIsBaptized(domain.getIsBaptized());
        entity.setContact(domain.getCellphoneNumber().getNumber());
        entity.setIssuingAuthority(domain.getIssuingAuthority());
        entity.setUnit(UnitEntityMapper.toEntity(domain.getUnit()));
        entity.setUnitRole(domain.getUnitRole());
        entity.setClassCategory(domain.getClassCategory());
        entity.setClassRole(domain.getClassRole());
        entity.setFatherName(domain.getFatherContact().getName());
        entity.setFatherContact(domain.getFatherContact().getCellphoneNumber().getNumber());
        entity.setFatherEmail(domain.getFatherContact().getEmail().getValue());
        entity.setMotherName(domain.getMotherContact().getName());
        entity.setMotherContact(domain.getMotherContact().getCellphoneNumber().getNumber());
        entity.setMotherEmail(domain.getMotherContact().getEmail().getValue());
        entity.setResponsibleName(domain.getResponsibleContact().getName());
        entity.setResponsibleContact(domain.getResponsibleContact().getCellphoneNumber().getNumber());
        entity.setResponsibleEmail(domain.getResponsibleContact().getEmail().getValue());
        entity.setAddress(AddressEntityMapper.toEntity(domain.getAddress()));
        entity.setMedicalData(MedicalDataEntityMapper.toEntity(domain.getMedicalData()));
        entity.setAcceptTerms(domain.getAcceptTerms());
        return entity;
    }

    public static MemberData toDomain(MemberDataEntity entity) {
        if (entity == null) return null;
        return MemberData.of(
                entity.getCpf(),
                entity.getImage(),
                entity.getImageFormat(),
                entity.getUsername(),
                entity.getBirthDate().toString(),
                entity.getSex(),
                entity.getBirthCertificate(),
                entity.getTshirtSize(),
                entity.getIsBaptized(),
                entity.getContact(),
                entity.getIssuingAuthority(),
                UnitEntityMapper.toDomain(entity.getUnit()),
                entity.getUnitRole(),
                entity.getClassCategory(),
                entity.getClassRole(),
                MemberContact.of(
                        entity.getFatherName(),
                        entity.getFatherContact(),
                        entity.getFatherEmail()
                ),
                MemberContact.of(
                        entity.getMotherName(),
                        entity.getMotherContact(),
                        entity.getMotherEmail()
                ),
                MemberContact.of(
                        entity.getResponsibleName(),
                        entity.getResponsibleContact(),
                        entity.getResponsibleEmail()
                ),
                AddressEntityMapper.toDomain(entity.getAddress()),
                MedicalDataEntityMapper.toDomain(entity.getMedicalData()),
                entity.getAcceptTerms()
        );
    }

    public static List<MemberData> toDomain(List<MemberDataEntity> entities) {
        if (entities == null) return null;
        return entities.stream().map(MemberDataEntityMapper::toDomain).collect(Collectors.toList());
    }
}
