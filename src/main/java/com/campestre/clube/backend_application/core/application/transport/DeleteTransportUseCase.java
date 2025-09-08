package com.campestre.clube.backend_application.core.application.transport;

import com.campestre.clube.backend_application.core.adapter.TransportGateway;
import com.campestre.clube.backend_application.core.application.exceptions.ConflictException;
import com.campestre.clube.backend_application.core.application.exceptions.NotFoundException;
import com.campestre.clube.backend_application.core.application.transport.command.DeleteTransportCommand;
import com.campestre.clube.backend_application.core.application.transport.command.UpdateTransportCommand;
import com.campestre.clube.backend_application.core.domain.Transport;
import com.campestre.clube.backend_application.core.domain.valueobject.Contact;

public class DeleteTransportUseCase {

    private final TransportGateway gateway;

    public DeleteTransportUseCase(TransportGateway gateway) {
        this.gateway = gateway;
    }

    public void execute(DeleteTransportCommand command) {
        if (gateway.existsById(command.id()))
            throw new NotFoundException("O transporte não foi encontrado");
        gateway.removeById(command.id());
    }
}
