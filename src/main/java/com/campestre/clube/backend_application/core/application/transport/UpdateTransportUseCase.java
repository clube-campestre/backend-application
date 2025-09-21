package com.campestre.clube.backend_application.core.application.transport;

import com.campestre.clube.backend_application.core.adapter.TransportGateway;
import com.campestre.clube.backend_application.core.application.exceptions.ConflictException;
import com.campestre.clube.backend_application.core.application.transport.command.UpdateTransportCommand;
import com.campestre.clube.backend_application.core.domain.Transport;
import com.campestre.clube.backend_application.core.domain.valueobject.Contact;

public class UpdateTransportUseCase {

    private final TransportGateway gateway;

    public UpdateTransportUseCase(TransportGateway gateway) {
        this.gateway = gateway;
    }

    public Transport execute(UpdateTransportCommand command) {
        if (gateway.existsById(command.id()))
            throw new ConflictException("O transporte não foi encontrado");

        if (gateway.existsByCompanyIgnoreCaseAndDriverIgnoreCaseAndIdNot(
                Contact.of(command.companyName(), command.companyNumber()),
                Contact.of(command.driverName(), command.driverNumber()),
                command.id()
        ))
            throw new ConflictException("A empresa e o motorista do transporte não pode ser repetido");
        Transport transport = Transport.of(
                command.id(),
                command.price(),
                command.travelDistance(),
                command.capacity(),
                command.companyName(),
                command.companyNumber(),
                command.driverName(),
                command.driverNumber(),
                command.rating()
        );
        return gateway.save(transport);
    }
}
