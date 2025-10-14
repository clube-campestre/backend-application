package com.campestre.clube.backend_application.infrastructure.persistence.jpa.memberdata;

import com.campestre.clube.backend_application.core.domain.enums.*;
import com.campestre.clube.backend_application.infrastructure.persistence.jpa.address.AddressEntity;
import com.campestre.clube.backend_application.infrastructure.persistence.jpa.medicaldata.MedicalDataEntity;
import com.campestre.clube.backend_application.infrastructure.persistence.jpa.unit.UnitEntity;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name= "member_data")
public class MemberDataEntity {
    @Id
    private String cpf;
    private String username;
    private LocalDate birthDate;
    @Enumerated(EnumType.STRING)
    private Sex sex;
    private String birthCertificate;
    @Enumerated(EnumType.STRING)
    private TshirtSize tshirtSize;
    private Boolean isBaptized = false;
    private String contact;
    private String issuingAuthority;

    @Lob
    @Column(name = "image", columnDefinition = "LONGBLOB")
    private byte[] image;
    @Column(name = "image_format", length = 50)
    private String imageFormat;

    @ManyToOne
    @JoinColumn(name = "fk_unit", nullable = false)
    private UnitEntity unit;
    private UnitRole unitRole;
    @Enumerated(EnumType.STRING)
    private ClassCategory classCategory;
    @Enumerated(EnumType.STRING)
    private ClassRole classRole;

    private String fatherName;
    private String fatherContact;
    private String fatherEmail;
    private String motherName;
    private String motherContact;
    private String motherEmail;
    private String responsibleName;
    private String responsibleContact;
    private String responsibleEmail;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "fk_address", nullable = false)
    private AddressEntity address;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "fk_medical_data", nullable = false)
    private MedicalDataEntity medicalData;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getImageFormat() {
        return imageFormat;
    }

    public void setImageFormat(String imageFormat) {
        this.imageFormat = imageFormat;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getBirthCertificate() {
        return birthCertificate;
    }

    public void setBirthCertificate(String birthCertificate) {
        this.birthCertificate = birthCertificate;
    }

    public TshirtSize getTshirtSize() {
        return tshirtSize;
    }

    public void setTshirtSize(TshirtSize tshirtSize) {
        this.tshirtSize = tshirtSize;
    }

    public Boolean getBaptized() {
        return isBaptized;
    }

    public void setBaptized(Boolean baptized) {
        isBaptized = baptized;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getIssuingAuthority() {
        return issuingAuthority;
    }

    public void setIssuingAuthority(String issuingAuthority) {
        this.issuingAuthority = issuingAuthority;
    }

    public UnitEntity getUnit() {
        return unit;
    }

    public void setUnit(UnitEntity unit) {
        this.unit = unit;
    }

    public UnitRole getUnitRole() {
        return unitRole;
    }

    public void setUnitRole(UnitRole unitRole) {
        this.unitRole = unitRole;
    }

    public ClassCategory getClassCategory() {
        return classCategory;
    }

    public void setClassCategory(ClassCategory classCategory) {
        this.classCategory = classCategory;
    }

    public ClassRole getClassRole() {
        return classRole;
    }

    public void setClassRole(ClassRole classRole) {
        this.classRole = classRole;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getFatherContact() {
        return fatherContact;
    }

    public void setFatherContact(String fatherContact) {
        this.fatherContact = fatherContact;
    }

    public String getFatherEmail() {
        return fatherEmail;
    }

    public void setFatherEmail(String fatherEmail) {
        this.fatherEmail = fatherEmail;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getMotherContact() {
        return motherContact;
    }

    public void setMotherContact(String motherContact) {
        this.motherContact = motherContact;
    }

    public String getMotherEmail() {
        return motherEmail;
    }

    public void setMotherEmail(String motherEmail) {
        this.motherEmail = motherEmail;
    }

    public String getResponsibleName() {
        return responsibleName;
    }

    public void setResponsibleName(String responsibleName) {
        this.responsibleName = responsibleName;
    }

    public String getResponsibleContact() {
        return responsibleContact;
    }

    public void setResponsibleContact(String responsibleContact) {
        this.responsibleContact = responsibleContact;
    }

    public String getResponsibleEmail() {
        return responsibleEmail;
    }

    public void setResponsibleEmail(String responsibleEmail) {
        this.responsibleEmail = responsibleEmail;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    public MedicalDataEntity getMedicalData() {
        return medicalData;
    }

    public void setMedicalData(MedicalDataEntity medicalData) {
        this.medicalData = medicalData;
    }
}
