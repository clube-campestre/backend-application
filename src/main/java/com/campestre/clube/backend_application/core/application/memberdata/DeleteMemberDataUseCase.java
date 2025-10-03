package com.campestre.clube.backend_application.core.application.memberdata;

import com.campestre.clube.backend_application.core.adapter.HasherGateway;
import com.campestre.clube.backend_application.core.adapter.MemberDataGateway;
import com.campestre.clube.backend_application.core.application.memberdata.command.DeleteMemberDataCommand;

import static com.campestre.clube.backend_application.core.application.utils.ExceptionExtensions.NOT_FOUND_MEMBER_DATA;

public class DeleteMemberDataUseCase {

    private final MemberDataGateway gateway;
    private final HasherGateway hasherGateway;

    public DeleteMemberDataUseCase(MemberDataGateway gateway, HasherGateway hasherGateway) {
        this.gateway = gateway;
        this.hasherGateway = hasherGateway;
    }

    public void execute(DeleteMemberDataCommand command) {
        String cpfHash = hasherGateway.crypt(command.cpf());
        if (!gateway.existsByCpf(cpfHash)) throw NOT_FOUND_MEMBER_DATA;
        gateway.removeByCpf(cpfHash);
    }
}
