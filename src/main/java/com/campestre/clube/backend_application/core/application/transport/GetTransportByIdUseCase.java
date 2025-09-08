package com.campestre.clube.backend_application.core.application.transport;

import com.campestre.clube.backend_application.core.adapter.TransportGateway;
import com.campestre.clube.backend_application.core.application.exceptions.NotFoundException;
import com.campestre.clube.backend_application.core.application.transport.command.GetTransportByIdCommand;
import com.campestre.clube.backend_application.core.domain.Transport;

public class GetTransportByIdUseCase {

    private final TransportGateway gateway;

    public GetTransportByIdUseCase(TransportGateway gateway) {
        this.gateway = gateway;
    }

    public Transport execute(GetTransportByIdCommand command) {
        if (gateway.existsById(command.id()))
            throw new NotFoundException("O transporte não foi encontrado");
        return gateway.findById(command.id());
    }
}
