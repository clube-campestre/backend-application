package com.campestre.clube.backend_application.core.application.transport;

import com.campestre.clube.backend_application.core.adapter.TransportGateway;
import com.campestre.clube.backend_application.core.application.transport.command.GetTransportByIdCommand;
import com.campestre.clube.backend_application.core.domain.Transport;

import static com.campestre.clube.backend_application.core.application.utils.ExceptionExtensions.NOT_FOUND_TRANSPORT;

public class GetTransportByIdUseCase {

    private final TransportGateway gateway;

    public GetTransportByIdUseCase(TransportGateway gateway) {
        this.gateway = gateway;
    }

    public Transport execute(GetTransportByIdCommand command) {
        if (!gateway.existsById(command.id())) throw NOT_FOUND_TRANSPORT;
        return gateway.findById(command.id());
    }
}
