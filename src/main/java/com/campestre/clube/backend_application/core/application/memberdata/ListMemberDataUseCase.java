package com.campestre.clube.backend_application.core.application.memberdata;

import com.campestre.clube.backend_application.core.adapter.HasherGateway;
import com.campestre.clube.backend_application.core.adapter.MemberDataGateway;
import com.campestre.clube.backend_application.core.domain.MemberData;
import com.campestre.clube.backend_application.core.domain.valueobject.Cns;
import com.campestre.clube.backend_application.core.domain.valueobject.Cpf;

import java.util.List;

public class ListMemberDataUseCase {

    private final MemberDataGateway gateway;
    private final HasherGateway hasherGateway;

    public ListMemberDataUseCase(MemberDataGateway gateway, HasherGateway hasherGateway) {
        this.gateway = gateway;
        this.hasherGateway = hasherGateway;
    }

    public List<MemberData> execute() {
        return gateway.findAll().stream().map(member -> {
            member.setCpf(Cpf.of(hasherGateway.decrypt(member.getCpf().getNumber())));

            if (member.getMedicalData() != null) {
                if (member.getMedicalData().getCpf() != null) member.getMedicalData()
                        .setCpf(Cpf.of(hasherGateway.decrypt(member.getMedicalData().getCpf().getNumber())));
                if (member.getMedicalData().getCns() != null) member.getMedicalData()
                        .setCns(Cns.of(hasherGateway.decrypt(member.getMedicalData().getCns().getNumber())));
            }
            if (member.getAddress() != null) {
                if (member.getAddress().getHouseNumber() != null) member.getAddress()
                        .setHouseNumber(hasherGateway.decrypt(member.getAddress().getHouseNumber()));
                if (member.getAddress().getReferenceHouse() != null) member.getAddress()
                        .setReferenceHouse(hasherGateway.decrypt(member.getAddress().getReferenceHouse()));
            }

            return member;
        }).toList();
    }
}
