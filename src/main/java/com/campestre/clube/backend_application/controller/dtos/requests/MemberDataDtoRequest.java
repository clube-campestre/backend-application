package com.campestre.clube.backend_application.controller.dtos.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class MemberDataDtoRequest {

    @NotBlank
    @Size(min = 11, max = 11)
    private String cpf;

    private String idImage;

    private String imagePath;

    @NotNull
    @Schema(description = "Member data unit role", example = "CONSELHEIRO", allowableValues = {
            "CONSELHEIRO", "CONSELHEIRO_AUXILIAR", "CAPITAO", "VICE_CAPITAO", "TESOUREIRO", "VICE_TESOUREIRO",
            "SECRETARIO", "VICE_SECRETARIO", "PADIOLEIRO", "CAPELAO", "ALMO_XARIFADO"
    })
    private String unitRole; // Enum: função dentro da unidade (ex: CONSELHEIRO, INSTRUTOR)

    @NotBlank
    @Size(max = 50)
    private String username;

    @NotNull
    @Past
    private LocalDate birthDate;

    @NotNull
    @Schema(description = "Member data sex", example = "MASCULINO", allowableValues = {"OUTRO", "FEMININO", "MASCULINO"})
    private String sex; // Enum: "M", "F", "OUTRO"

    @NotBlank
    @Size(max = 32)
    private String birthCertificate;

    @NotNull
    @Schema(description = "Member data t-shirt size", example = "M", allowableValues = {
            "PP", "P", "M", "G", "GG", "XG", "XG1", "XG2"
    })
    private String tshirtSize; // Ex: "PP", "M", "G"

    @NotNull
    private Boolean isBaptized;

    @NotBlank
    @Size(max = 12)
    private String contact;

//    TODO validar se precisa
    @NotNull
    private Integer unitId;

    @NotNull
    @Schema(description = "Member data class category", example = "AMIGO", allowableValues = {
            "AMIGO", "COMPANHEIRO", "PESQUISADOR", "PIONEIRO", "EXCURSIONISTA", "GUIA", "AGRUPADAS",
            "DESBRAVADORES_COMPLETO", "LIDER", "LIDER_MASTER", "LIDER_MASTER_AVANCADO"
    })
    private String classCategory; // Enum: AMIGO, COMPANHEIRO, etc.

    @NotNull
    @Schema(description = "Member data class role", example = "INSTRUTOR", allowableValues = {
            "INSTRUTOR", "INSTRUTOR_AUXILIAR", "MEMBRO"
    })
    private String classRole; // Enum: INSTRUTOR, INSTRUTOR_AUXILIAR, MEMBRO

    // Informações do pai
    @Size(max = 50)
    private String fatherName;

    @Size(max = 12)
    private String fatherContact;

    @Email
    @Size(max = 50)
    private String fatherEmail;

    // Informações da mãe
    @Size(max = 50)
    private String motherName;

    @Size(max = 12)
    private String motherContact;

    @Email
    @Size(max = 50)
    private String motherEmail;

    // Responsável
    @Size(max = 50)
    private String responsibleName;

    @Size(max = 12)
    private String responsibleContact;

    @Email
    @Size(max = 50)
    private String responsibleEmail;


    @Valid
    private SaveMedicalDataRequestDto medicalData; // DTO dos dados médicos

    @Valid
    private SaveAddressRequestDto address; //DTO do endereço

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

    public String getUnitRole() {
        return unitRole;
    }

    public void setUnitRole(String unitRole) {
        this.unitRole = unitRole;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthCertificate() {
        return birthCertificate;
    }

    public void setBirthCertificate(String birthCertificate) {
        this.birthCertificate = birthCertificate;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
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

    public SaveMedicalDataRequestDto getMedicalData() {
        return medicalData;
    }

    public void setMedicalData(SaveMedicalDataRequestDto medicalData) {
        this.medicalData = medicalData;
    }

    public SaveAddressRequestDto getAddress() {
        return address;
    }

    public void setAddress(SaveAddressRequestDto address) {
        this.address = address;
    }
}
