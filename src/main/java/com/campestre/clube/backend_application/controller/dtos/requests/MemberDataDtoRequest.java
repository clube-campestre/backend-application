package com.campestre.clube.backend_application.controller.dtos.requests;

import com.campestre.clube.backend_application.entity.enums.ClassCategory;
import com.campestre.clube.backend_application.entity.enums.Sex;
import com.campestre.clube.backend_application.entity.enums.TshirtSize;
import com.campestre.clube.backend_application.entity.enums.UnitRole;
import com.campestre.clube.backend_application.entity.enums.ClassRole;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.Date;

public class MemberDataDtoRequest {

    @NotBlank
    @Size(min = 11, max = 11)
    private String cpf;

    private String idImage;

    private String imagePath;

    @NotNull
    private UnitRole unitRole; // Enum: função dentro da unidade (ex: CONSELHEIRO, INSTRUTOR)

    @NotBlank
    @Size(max = 50)
    private String username;

    @NotNull
    @Past
    private LocalDate birthDate;

    @NotNull
    private Sex sex; // Enum: "M", "F", "OUTRO"

    @NotBlank
    @Size(max = 32)
    private String birthCertificate;

    @NotNull
    private TshirtSize tshirtSize; // Ex: "PP", "M", "G"

    @NotNull
    private Boolean isBaptized;

    @NotBlank
    @Size(max = 12)
    private String contact;

    @NotNull
    private Integer unitId;

    @NotNull
    private ClassCategory classCategory; // Enum: AMIGO, COMPANHEIRO, etc.

    @NotNull
    private ClassRole classRole; // Enum: INSTRUTOR, INSTRUTOR_AUXILIAR, MEMBRO

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

    @NotNull
    private Integer addressId;

    @NotNull
    private String medicalDataId;

    @Valid
    private SaveAddressRequestDto address; //DTO do endereço

    @Valid
    private SaveMedicalDataRequestDto medicalData; // DTO dos dados médicos


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

    public UnitRole getUnitRole() {
        return unitRole;
    }

    public void setUnitRole(UnitRole unitRole) {
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

    public Boolean getIsBaptized() {
        return isBaptized;
    }

    public void setIsBaptized(Boolean baptized) {
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

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getMedicalDataId() {
        return medicalDataId;
    }

    public void setMedicalDataId(String medicalDataId) {
        this.medicalDataId = medicalDataId;
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
}
