package com.campestre.clube.backend_application.core.application.memberdata;

import com.campestre.clube.backend_application.core.adapter.HasherGateway;
import com.campestre.clube.backend_application.core.adapter.MemberDataGateway;
import com.campestre.clube.backend_application.core.application.memberdata.command.GetMemberDataByCpfCommand;
import com.campestre.clube.backend_application.core.domain.MemberData;

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
        return gateway.findByCpf(cpfHash);
    }
}
