package com.campestre.clube.backend_application.infrastructure.web.dtos.memberdata;

import com.campestre.clube.backend_application.infrastructure.web.dtos.address.SaveAddressRequestDto;
import com.campestre.clube.backend_application.infrastructure.web.dtos.medicaldata.MedicalDataRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

public class MemberDataRequestDto {
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
//    @Past
    private String birthDate;

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
    private MedicalDataRequestDto medicalData;

    private String fatherName;
    private String fatherContact;
    private String fatherEmail;

    private String motherName;
    private String motherContact;
    private String motherEmail;

    private String responsibleName;
    private String responsibleContact;
    private String responsibleEmail;

    @NotNull
    @Schema(description = "Member data unit role", example = "CONSELHEIRO", allowableValues = {
            "NENHUM", "CONSELHEIRO", "CONSELHEIRO_AUXILIAR", "CAPITAO", "VICE_CAPITAO", "TESOUREIRO",
            "VICE_TESOUREIRO", "SECRETARIO", "VICE_SECRETARIO", "PADIOLEIRO", "CAPELAO", "ALMO_XARIFADO"
    })
    private String unitRole;

    @NotBlank
    private String unitName;

    @NotNull
    @Schema(description = "Member data class category", example = "AMIGO", allowableValues = {
            "NENHUMA", "AMIGO", "COMPANHEIRO", "PESQUISADOR", "PIONEIRO", "EXCURSIONISTA", "GUIA", "AGRUPADAS",
            "DESBRAVADORES_COMPLETO", "LIDER", "LIDER_MASTER", "LIDER_MASTER_AVANCADO"
    })
    private String classCategory;

    @NotNull
    @Schema(description = "Member data class role", example = "INSTRUTOR", allowableValues = {
            "NENHUMA", "INSTRUTOR", "INSTRUTOR_AUXILIAR", "MEMBRO"
    })
    private String classRole;

    @AssertTrue
    @NotNull
    private Boolean acceptTerms;

    public MemberDataRequestDto() {}

    public MemberDataRequestDto(
            String username, String birthCertificate, String cpf, String issuingAuthority, String contact,
            String birthDate, String sex, String tshirtSize, Boolean isBaptized, SaveAddressRequestDto address,
            MedicalDataRequestDto medicalData, String fatherName, String fatherContact, String fatherEmail,
            String motherName, String motherContact, String motherEmail, String responsibleName,
            String responsibleContact, String responsibleEmail, String unitRole, String unitName, String classCategory,
            String classRole, Boolean acceptTerms
    ) {
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
        this.unitName = unitName;
        this.classCategory = classCategory;
        this.classRole = classRole;
        this.acceptTerms = acceptTerms;
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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
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

    public Boolean getBaptized() {
        return isBaptized;
    }

    public void setBaptized(Boolean baptized) {
        isBaptized = baptized;
    }

    public SaveAddressRequestDto getAddress() {
        return address;
    }

    public void setAddress(SaveAddressRequestDto address) {
        this.address = address;
    }

    public MedicalDataRequestDto getMedicalData() {
        return medicalData;
    }

    public void setMedicalData(MedicalDataRequestDto medicalData) {
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

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
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

    public Boolean getAcceptTerms() {
        return acceptTerms;
    }

    public void setAcceptTerms(Boolean acceptTerms) {
        this.acceptTerms = acceptTerms;
    }
}
