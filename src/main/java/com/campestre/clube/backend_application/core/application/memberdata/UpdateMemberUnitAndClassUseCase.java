package com.campestre.clube.backend_application.core.application.memberdata;

import com.campestre.clube.backend_application.core.adapter.HasherGateway;
import com.campestre.clube.backend_application.core.adapter.MemberDataGateway;
import com.campestre.clube.backend_application.core.adapter.UnitGateway;
import com.campestre.clube.backend_application.core.application.memberdata.command.UpdateMemberDataCommand;
import com.campestre.clube.backend_application.core.application.memberdata.command.UpdateMemberUnitAndClassCommand;
import com.campestre.clube.backend_application.core.domain.*;
import com.campestre.clube.backend_application.core.domain.valueobject.Cns;
import com.campestre.clube.backend_application.core.domain.valueobject.Cpf;

import static com.campestre.clube.backend_application.core.exceptions.ExceptionExtensions.*;

public class UpdateMemberUnitAndClassUseCase {

    private final MemberDataGateway gateway;
    private final UnitGateway unitGateway;
    private final HasherGateway hasherGateway;

    public UpdateMemberUnitAndClassUseCase(
            MemberDataGateway gateway, UnitGateway unitGateway, HasherGateway hasherGateway
    ) {
        this.gateway = gateway;
        this.unitGateway = unitGateway;
        this.hasherGateway = hasherGateway;
    }

    public MemberData execute(UpdateMemberUnitAndClassCommand command) {
        String cpfHash = hasherGateway.encrypt(command.cpf());

        if (!gateway.existsByCpf(cpfHash)) throw NOT_FOUND_MEMBER_DATA;
        if (!unitGateway.existsBySurnameIgnoreCase(command.unitName())) throw NOT_FOUND_UNIT;

        MemberData memberData = gateway.findByCpf(cpfHash);
        Unit unit = unitGateway.findBySurnameIgnoreCase(command.unitName());

        MemberData newMemberData = MemberData.of(
                cpfHash,
                memberData.getImage().getValue(),
                memberData.getImage().getFormat(),
                memberData.getUsername(),
                memberData.getBirthDate(),
                memberData.getSex(),
                memberData.getBirthCertificate(),
                memberData.getTshirtSize(),
                memberData.getBaptized(),
                memberData.getCellphoneNumber().getNumber(),
                memberData.getIssuingAuthority(),
                unit,
                command.unitRole(),
                command.classCategory(),
                command.classRole(),
                memberData.getFatherContact(),
                memberData.getMotherContact(),
                memberData.getResponsibleContact(),
                memberData.getAddress(),
                memberData.getMedicalData(),
                memberData.getAcceptTerms()
        );

        MemberData savedMemberData = gateway.save(newMemberData);
        savedMemberData.setCpf(Cpf.of(
                hasherGateway.decrypt(savedMemberData.getAddress().getHouseNumber())
        ));
        savedMemberData.getMedicalData().setCpf(Cpf.of(
                hasherGateway.decrypt(savedMemberData.getMedicalData().getCpf().getNumber())
        ));
        savedMemberData.getMedicalData().setCns(Cns.of(
                hasherGateway.decrypt(savedMemberData.getMedicalData().getCns().getNumber())
        ));
        savedMemberData.getAddress().setHouseNumber(
                hasherGateway.decrypt(savedMemberData.getAddress().getHouseNumber())
        );
        savedMemberData.getAddress().setReferenceHouse(
                hasherGateway.decrypt(savedMemberData.getAddress().getReferenceHouse())
        );

        return savedMemberData;
    }
}
