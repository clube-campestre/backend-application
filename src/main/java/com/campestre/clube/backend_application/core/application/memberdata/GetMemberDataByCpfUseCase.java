package com.campestre.clube.backend_application.core.application.memberdata;

import com.campestre.clube.backend_application.core.adapter.HasherGateway;
import com.campestre.clube.backend_application.core.adapter.MemberDataGateway;
import com.campestre.clube.backend_application.core.application.memberdata.command.GetMemberDataByCpfCommand;
import com.campestre.clube.backend_application.core.domain.Address;
import com.campestre.clube.backend_application.core.domain.MedicalData;
import com.campestre.clube.backend_application.core.domain.MemberData;
import com.campestre.clube.backend_application.core.domain.valueobject.Cns;
import com.campestre.clube.backend_application.core.domain.valueobject.Cpf;

import static com.campestre.clube.backend_application.core.application.utils.ExceptionExtensions.NOT_FOUND_MEMBER_DATA;

public class GetMemberDataByCpfUseCase {

    private final MemberDataGateway gateway;
    private final HasherGateway hasherGateway;

    public GetMemberDataByCpfUseCase(MemberDataGateway gateway, HasherGateway hasherGateway) {
        this.gateway = gateway;
        this.hasherGateway = hasherGateway;
    }

    public MemberData execute(GetMemberDataByCpfCommand command) {
        String cpfHash = hasherGateway.crypt(command.cpf());
        if (!gateway.existsByCpf(cpfHash)) throw NOT_FOUND_MEMBER_DATA;

        MemberData memberData = gateway.findByCpf(cpfHash);
        memberData.setCpf(Cpf.of(hasherGateway.decrypt(memberData.getCpf().getNumber())));

        MedicalData medicalData = memberData.getMedicalData();
        medicalData.setCpf(Cpf.of(hasherGateway.decrypt(medicalData.getCpf().getNumber())));
        medicalData.setCns(Cns.of(hasherGateway.decrypt(medicalData.getCns().getNumber())));

        Address address = memberData.getAddress();
        address.setHouseNumber(hasherGateway.decrypt(address.getHouseNumber()));
        address.setReferenceHouse(hasherGateway.decrypt(address.getReferenceHouse()));

        return memberData;
    }
}
