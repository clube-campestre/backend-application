package com.campestre.clube.backend_application.entity;

import com.campestre.clube.backend_application.entity.enums.ClassCategory;
import com.campestre.clube.backend_application.entity.enums.Sex;
import com.campestre.clube.backend_application.entity.enums.TshirtSize;
import com.campestre.clube.backend_application.entity.enums.UnitRole;
import com.campestre.clube.backend_application.entity.enums.ClassRole;
import jakarta.persistence.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Entity(name= "member_data")
public class MemberData {
    @Id
    @CPF
    private String cpf;
    private String idImage;
    private String imagePath;
    private String username;
    private LocalDate birthDate;
    @Enumerated(EnumType.STRING)
    private Sex sex;
    private String birthCertificate;
    @Enumerated(EnumType.STRING)
    private TshirtSize tshirtSize;
    private Boolean isBaptized;
    private String contact;
    private String issuingAuthority;

    @ManyToOne
    @JoinColumn(name = "fk_unit")
    private Unit unit;
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

    @ManyToOne
    @JoinColumn(name = "fk_address")
    private Address address;

    @OneToOne
    @JoinColumn(name = "fk_medical_data")
    private MedicalData medicalData;

    public MemberData(String cpf, String idImage, String imagePath, String username, LocalDate birthDate, Sex sex, String birthCertificate, TshirtSize tshirtSize, Boolean isBaptized, String contact, String issuingAuthority, Unit unit, UnitRole unitRole, ClassCategory classCategory, ClassRole classRole, String fatherName, String fatherContact, String fatherEmail, String motherName, String motherContact, String motherEmail, String responsibleName, String responsibleContact, String responsibleEmail, Address address, MedicalData medicalData) {
        this.cpf = cpf;
        this.idImage = idImage;
        this.imagePath = imagePath;
        this.username = username;
        this.birthDate = birthDate;
        this.sex = sex;
        this.birthCertificate = birthCertificate;
        this.tshirtSize = tshirtSize;
        this.isBaptized = isBaptized;
        this.contact = contact;
        this.issuingAuthority = issuingAuthority;
        this.unit = unit;
        this.unitRole = unitRole;
        this.classCategory = classCategory;
        this.classRole = classRole;
        this.fatherName = fatherName;
        this.fatherContact = fatherContact;
        this.fatherEmail = fatherEmail;
        this.motherName = motherName;
        this.motherContact = motherContact;
        this.motherEmail = motherEmail;
        this.responsibleName = responsibleName;
        this.responsibleContact = responsibleContact;
        this.responsibleEmail = responsibleEmail;
        this.address = address;
        this.medicalData = medicalData;
    }

    public MemberData() {
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getIdImage() {
        return idImage;
    }

    public void setIdImage(String idImage) {
        this.idImage = idImage;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public MedicalData getMedicalData() {
        return medicalData;
    }

    public void setMedicalData(MedicalData medicalData) {
        this.medicalData = medicalData;
    }

    public Boolean getIsBaptized() {
        return isBaptized;
    }

    public void setIsBaptized(Boolean isBaptized) {
        this.isBaptized = isBaptized;
    }

    public String getIssuingAuthority() {
        return issuingAuthority;
    }

    public void setIssuingAuthority(String issuingAuthority) {
        this.issuingAuthority = issuingAuthority;
    }
}
