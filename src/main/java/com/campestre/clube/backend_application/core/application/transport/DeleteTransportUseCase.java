package com.campestre.clube.backend_application.core.application.transport;

import com.campestre.clube.backend_application.core.adapter.TransportGateway;
import com.campestre.clube.backend_application.core.application.transport.command.DeleteTransportCommand;

import static com.campestre.clube.backend_application.core.application.utils.ExceptionExtensions.NOT_FOUND_TRANSPORT;

public class DeleteTransportUseCase {

    private final TransportGateway gateway;

    public DeleteTransportUseCase(TransportGateway gateway) {
        this.gateway = gateway;
    }

    public void execute(DeleteTransportCommand command) {
        if (!gateway.existsById(command.id())) throw NOT_FOUND_TRANSPORT;
        gateway.removeById(command.id());
    }
}
