package com.campestre.clube.backend_application.controller.dtos.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class MemberDataDtoRequest {

    private String idImage;

    private String imagePath;

    @NotBlank
    @Size(max = 255)
    private String username;

    @NotBlank
    @Size(max = 32)
    private String birthCertificate;

    @NotBlank
    @Size(min = 11, max = 11)
    private String cpf;

    @NotBlank
    private String issuingAuthority;

    @NotBlank
    @Size(min = 11, max = 13)
    private String contact;

    @NotNull
    @Past
    private LocalDate birthDate;

    @NotNull
    @Schema(description = "Member data sex", example = "MASCULINO", allowableValues = {"OUTRO", "FEMININO", "MASCULINO"})
    private String sex;

    @NotNull
    @Schema(description = "Member data t-shirt size", example = "M", allowableValues = {
            "PP", "P", "M", "G", "GG", "XG", "XG1", "XG2"
    })
    private String tshirtSize;

    @NotNull
    private Boolean isBaptized;

    @Valid
    private SaveAddressRequestDto address;

    @Valid
    private SaveMedicalDataRequestDto medicalData;

    @Size(max = 255)
    private String fatherName;
    @Size(min = 11, max = 13)
    private String fatherContact;
    @Email
    @Size(max = 255)
    private String fatherEmail;

    @Size(max = 255)
    private String motherName;
    @Size(min = 11, max = 13)
    private String motherContact;
    @Email
    @Size(max = 255)
    private String motherEmail;

    @Size(max = 255)
    private String responsibleName;
    @Size(min = 11, max = 13)
    private String responsibleContact;
    @Email
    @Size(max = 255)
    private String responsibleEmail;

    @NotNull
    @Schema(description = "Member data unit role", example = "CONSELHEIRO", allowableValues = {
            "CONSELHEIRO", "CONSELHEIRO_AUXILIAR", "CAPITAO", "VICE_CAPITAO", "TESOUREIRO", "VICE_TESOUREIRO",
            "SECRETARIO", "VICE_SECRETARIO", "PADIOLEIRO", "CAPELAO", "ALMO_XARIFADO"
    })
    private String unitRole;

    @Valid
    private UnitRequestDto unit;

    @NotNull
    @Schema(description = "Member data class category", example = "AMIGO", allowableValues = {
            "AMIGO", "COMPANHEIRO", "PESQUISADOR", "PIONEIRO", "EXCURSIONISTA", "GUIA", "AGRUPADAS",
            "DESBRAVADORES_COMPLETO", "LIDER", "LIDER_MASTER", "LIDER_MASTER_AVANCADO"
    })
    private String classCategory;

    @NotNull
    @Schema(description = "Member data class role", example = "INSTRUTOR", allowableValues = {
            "INSTRUTOR", "INSTRUTOR_AUXILIAR", "MEMBRO"
    })
    private String classRole;

    public MemberDataDtoRequest() {
    }

    public MemberDataDtoRequest(String idImage, String imagePath, String username, String birthCertificate, String cpf, String issuingAuthority, String contact, LocalDate birthDate, String sex, String tshirtSize, Boolean isBaptized, SaveAddressRequestDto address, SaveMedicalDataRequestDto medicalData, String fatherName, String fatherContact, String fatherEmail, String motherName, String motherContact, String motherEmail, String responsibleName, String responsibleContact, String responsibleEmail, String unitRole, UnitRequestDto unit, String classCategory, String classRole) {
        this.idImage = idImage;
        this.imagePath = imagePath;
        this.username = username;
        this.birthCertificate = birthCertificate;
        this.cpf = cpf;
        this.issuingAuthority = issuingAuthority;
        this.contact = contact;
        this.birthDate = birthDate;
        this.sex = sex;
        this.tshirtSize = tshirtSize;
        this.isBaptized = isBaptized;
        this.address = address;
        this.medicalData = medicalData;
        this.fatherName = fatherName;
        this.fatherContact = fatherContact;
        this.fatherEmail = fatherEmail;
        this.motherName = motherName;
        this.motherContact = motherContact;
        this.motherEmail = motherEmail;
        this.responsibleName = responsibleName;
        this.responsibleContact = responsibleContact;
        this.responsibleEmail = responsibleEmail;
        this.unitRole = unitRole;
        this.unit = unit;
        this.classCategory = classCategory;
        this.classRole = classRole;
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

    public String getBirthCertificate() {
        return birthCertificate;
    }

    public void setBirthCertificate(String birthCertificate) {
        this.birthCertificate = birthCertificate;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getIssuingAuthority() {
        return issuingAuthority;
    }

    public void setIssuingAuthority(String issuingAuthority) {
        this.issuingAuthority = issuingAuthority;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTshirtSize() {
        return tshirtSize;
    }

    public void setTshirtSize(String tshirtSize) {
        this.tshirtSize = tshirtSize;
    }

    public Boolean getIsBaptized() {
        return isBaptized;
    }

    public void setIsBaptized(Boolean isBaptized) {
        isBaptized = isBaptized;
    }

    public SaveAddressRequestDto getAddress() {
        return address;
    }

    public void setAddress(SaveAddressRequestDto address) {
        this.address = address;
    }

    public SaveMedicalDataRequestDto getMedicalData() {
        return medicalData;
    }

    public void setMedicalData(SaveMedicalDataRequestDto medicalData) {
        this.medicalData = medicalData;
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

    public String getUnitRole() {
        return unitRole;
    }

    public void setUnitRole(String unitRole) {
        this.unitRole = unitRole;
    }

    public UnitRequestDto getUnit() {
        return unit;
    }

    public void setUnit(UnitRequestDto unit) {
        this.unit = unit;
    }

    public String getClassCategory() {
        return classCategory;
    }

    public void setClassCategory(String classCategory) {
        this.classCategory = classCategory;
    }

    public String getClassRole() {
        return classRole;
    }

    public void setClassRole(String classRole) {
        this.classRole = classRole;
    }
}
