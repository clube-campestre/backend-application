package com.campestre.clube.backend_application.core.application.memberdata;

import com.campestre.clube.backend_application.core.adapter.MemberDataGateway;
import com.campestre.clube.backend_application.core.application.memberdata.command.DeleteMemberDataCommand;

import static com.campestre.clube.backend_application.core.application.utils.ExceptionExtensions.NOT_FOUND_MEMBER_DATA;

public class DeleteMemberDataUseCase {

    private final MemberDataGateway gateway;

    public DeleteMemberDataUseCase(MemberDataGateway gateway) {
        this.gateway = gateway;
    }

    public void execute(DeleteMemberDataCommand command) {
        if (!gateway.existsByCpf(command.cpf())) throw NOT_FOUND_MEMBER_DATA;
        gateway.removeByCpf(command.cpf());
    }
}
