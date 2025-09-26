package com.campestre.clube.backend_application.core.application.memberdata;

import com.campestre.clube.backend_application.core.adapter.MemberDataGateway;
import com.campestre.clube.backend_application.core.application.memberdata.command.GetMemberDataByCpfCommand;
import com.campestre.clube.backend_application.core.domain.MemberData;

import static com.campestre.clube.backend_application.core.application.extensions.ExceptionExtensions.NOT_FOUND_MEMBER_DATA;

public class GetMemberDataByCpfUseCase {

    private final MemberDataGateway gateway;

    public GetMemberDataByCpfUseCase(MemberDataGateway gateway) {
        this.gateway = gateway;
    }

    public MemberData execute(GetMemberDataByCpfCommand command) {
        if (!gateway.existsByCpf(command.cpf())) throw NOT_FOUND_MEMBER_DATA;
        return gateway.findByCpf(command.cpf());
    }
}
