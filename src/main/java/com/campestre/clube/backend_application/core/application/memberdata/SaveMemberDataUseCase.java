package com.campestre.clube.backend_application.core.application.memberdata;

import com.campestre.clube.backend_application.core.adapter.MedicalDataGateway;
import com.campestre.clube.backend_application.core.adapter.MemberDataGateway;
import com.campestre.clube.backend_application.core.adapter.UnitGateway;
import com.campestre.clube.backend_application.core.application.memberdata.command.SaveMemberDataCommand;
import com.campestre.clube.backend_application.core.domain.Address;
import com.campestre.clube.backend_application.core.domain.MedicalData;
import com.campestre.clube.backend_application.core.domain.MemberData;
import com.campestre.clube.backend_application.core.domain.Unit;
import com.campestre.clube.backend_application.core.domain.valueobject.*;

import static com.campestre.clube.backend_application.core.application.utils.ExceptionExtensions.CONFLICT_MEMBER_DATA_SAME_CPF;
import static com.campestre.clube.backend_application.core.application.utils.ExceptionExtensions.CONFLICT_MEMBER_DATA_SAME_CNS;
import static com.campestre.clube.backend_application.core.application.utils.ExceptionExtensions.NOT_FOUND_UNIT;

public class SaveMemberDataUseCase {

    private final MemberDataGateway gateway;
    private final MedicalDataGateway medicalDataGateway;
    private final UnitGateway unitGateway;

    public SaveMemberDataUseCase(
            MemberDataGateway gateway, MedicalDataGateway medicalDataGateway, UnitGateway unitGateway
    ) {
        this.gateway = gateway;
        this.medicalDataGateway = medicalDataGateway;
        this.unitGateway = unitGateway;
    }

    public MemberData execute(SaveMemberDataCommand command) {
        if(gateway.existsByCpf(command.cpf())) throw CONFLICT_MEMBER_DATA_SAME_CPF;
        if (medicalDataGateway.existsByCns(command.cns())) throw CONFLICT_MEMBER_DATA_SAME_CNS;
        if (!unitGateway.existsBySurnameIgnoreCase(command.unitName())) throw NOT_FOUND_UNIT;

        Unit unit = unitGateway.findBySurnameIgnoreCase(command.unitName());

        MemberData memberData = MemberData.of(
                command.cpf(),
                command.idImage(),
                command.imagePath(),
                command.username(),
                command.birthDate(),
                command.sex(),
                command.birthCertificate(),
                command.tshirtSize(),
                command.isBaptized(),
                command.cellphoneNumber(),
                command.issuingAuthority(),
                unit,
                command.unitRole(),
                command.classCategory(),
                command.classRole(),
                MemberContact.of(
                        command.fatherName(),
                        command.fatherCellphoneNumber(),
                        command.fatherEmail()
                ),
                MemberContact.of(
                        command.motherName(),
                        command.motherCellphoneNumber(),
                        command.motherEmail()
                ),
                MemberContact.of(
                        command.responsibleName(),
                        command.responsibleCellphoneNumber(),
                        command.responsibleEmail()
                ),
                Address.of(
                        command.addressStreet(),
                        command.addressHouseNumber(),
                        command.addressDistrict(),
                        command.addressState(),
                        command.addressCity(),
                        command.addressCepNumber(),
                        command.addressReferenceHouse()
                ),
                MedicalData.of(
                        command.cpf(),
                        command.cns(),
                        command.agreement(),
                        command.bloodType(),
                        command.catapora(),
                        command.meningite(),
                        command.hepatite(),
                        command.dengue(),
                        command.pneumonia(),
                        command.malaria(),
                        command.febreAmarela(),
                        command.sarampo(),
                        command.tetano(),
                        command.variola(),
                        command.coqueluche(),
                        command.difteria(),
                        command.rinite(),
                        command.bronquite(),
                        command.asma(),
                        command.rubeola(),
                        command.colera(),
                        command.covid19(),
                        command.h1n1(),
                        command.caxumba(),
                        command.others(),
                        command.heartProblems(),
                        command.drugAllergy(),
                        command.lactoseAllergy(),
                        command.deficiency(),
                        command.bloodTransfusion(),
                        command.haveSkinAllergy(),
                        command.skinAllergyMedication(),
                        command.haveFaintingOrConvulsion(),
                        command.faintingOrConvulsionMedication(),
                        command.psychologicalDisorder(),
                        command.haveAllergy(),
                        command.allergyMedication(),
                        command.haveDiabetic(),
                        command.diabeticMedication(),
                        command.recentSeriousInjury(),
                        command.recentFracture(),
                        command.surgeries(),
                        command.hospitalizationReasonLast5Years()
                )
        );
        return gateway.save(memberData);
    }
}
