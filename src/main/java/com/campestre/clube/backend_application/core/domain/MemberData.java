package com.campestre.clube.backend_application.core.domain;

import com.campestre.clube.backend_application.core.domain.enums.*;
import com.campestre.clube.backend_application.core.domain.valueobject.CellphoneNumber;
import com.campestre.clube.backend_application.core.domain.valueobject.Cpf;
import com.campestre.clube.backend_application.core.domain.valueobject.Image;
import com.campestre.clube.backend_application.core.domain.valueobject.MemberContact;

import java.time.LocalDate;

public class MemberData {
    private Cpf cpf;
    private Image image;
    private String username;
    private LocalDate birthDate;
    private Sex sex;
    private String birthCertificate;
    private TshirtSize tshirtSize;
    private Boolean isBaptized;
    private CellphoneNumber cellphoneNumber;
    private String issuingAuthority;

    private Unit unit;
    private UnitRole unitRole;
    private ClassCategory classCategory;
    private ClassRole classRole;

    private MemberContact fatherContact;
    private MemberContact motherContact;
    private MemberContact responsibleContact;

    private Address address;
    private MedicalData medicalData;

    public MemberData(
            Cpf cpf, Image image, String username, LocalDate birthDate, Sex sex,
            String birthCertificate, TshirtSize tshirtSize, Boolean isBaptized, CellphoneNumber cellphoneNumber,
            String issuingAuthority, Unit unit, UnitRole unitRole, ClassCategory classCategory, ClassRole classRole,
            MemberContact fatherContact, MemberContact motherContact, MemberContact responsibleContact, Address address,
            MedicalData medicalData
    ) {
        this.cpf = cpf;
        this.image = image;
        this.username = username;
        this.birthDate = birthDate;
        this.sex = sex;
        this.birthCertificate = birthCertificate;
        this.tshirtSize = tshirtSize;
        this.isBaptized = isBaptized;
        this.cellphoneNumber = cellphoneNumber;
        this.issuingAuthority = issuingAuthority;
        this.unit = unit;
        this.unitRole = unitRole;
        this.classCategory = classCategory;
        this.classRole = classRole;
        this.fatherContact = fatherContact;
        this.motherContact = motherContact;
        this.responsibleContact = responsibleContact;
        this.address = address;
        this.medicalData = medicalData;
    }

    public static MemberData of(
            String cpf, byte[] image, String imageFormat, String username, LocalDate birthDate, Sex sex,
            String birthCertificate, TshirtSize tshirtSize, Boolean isBaptized, String cellphoneNumber,
            String issuingAuthority, Unit unit, UnitRole unitRole, ClassCategory classCategory, ClassRole classRole,
            MemberContact fatherContact, MemberContact motherContact, MemberContact responsibleContact, Address address,
            MedicalData medicalData
    ) {
        return new MemberData(
                Cpf.of(cpf), Image.of(image, imageFormat), username, birthDate, sex, birthCertificate, tshirtSize, isBaptized,
                CellphoneNumber.of(cellphoneNumber), issuingAuthority, unit, unitRole, classCategory, classRole,
                fatherContact, motherContact, responsibleContact, address, medicalData
        );
    }

    public Cpf getCpf() {
        return cpf;
    }

    public void setCpf(Cpf cpf) {
        this.cpf = cpf;
    }

    public Image getImage() {
        return image;
    }

    public String getUsername() {
        return username;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Sex getSex() {
        return sex;
    }

    public String getBirthCertificate() {
        return birthCertificate;
    }

    public TshirtSize getTshirtSize() {
        return tshirtSize;
    }

    public Boolean getBaptized() {
        return isBaptized;
    }

    public CellphoneNumber getCellphoneNumber() {
        return cellphoneNumber;
    }

    public String getIssuingAuthority() {
        return issuingAuthority;
    }

    public Unit getUnit() {
        return unit;
    }

    public UnitRole getUnitRole() {
        return unitRole;
    }

    public ClassCategory getClassCategory() {
        return classCategory;
    }

    public ClassRole getClassRole() {
        return classRole;
    }

    public MemberContact getFatherContact() {
        return fatherContact;
    }

    public MemberContact getMotherContact() {
        return motherContact;
    }

    public MemberContact getResponsibleContact() {
        return responsibleContact;
    }

    public Address getAddress() {
        return address;
    }

    public MedicalData getMedicalData() {
        return medicalData;
    }
}
