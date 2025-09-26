package com.campestre.clube.backend_application.core.application.memberdata;

import com.campestre.clube.backend_application.core.adapter.TransportGateway;
import com.campestre.clube.backend_application.core.application.transport.command.DeleteTransportCommand;

import static com.campestre.clube.backend_application.core.application.extensions.ExceptionExtensions.NOT_FOUND_TRANSPORT;

public class DeleteMemberDataUseCase {

    private final TransportGateway gateway;

    public DeleteMemberDataUseCase(TransportGateway gateway) {
        this.gateway = gateway;
    }

    public void execute(DeleteTransportCommand command) {
        if (!gateway.existsById(command.id())) throw NOT_FOUND_TRANSPORT;
        gateway.removeById(command.id());
    }
}
