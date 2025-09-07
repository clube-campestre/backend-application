package com.campestre.clube.backend_application.core.application.transport;

import com.campestre.clube.backend_application.core.adapter.TransportGateway;
import com.campestre.clube.backend_application.core.application.exceptions.ConflictException;
import com.campestre.clube.backend_application.core.application.transport.command.SaveTransportCommand;
import com.campestre.clube.backend_application.core.domain.Transport;
import com.campestre.clube.backend_application.core.domain.valueobject.Contact;

public class SaveTransportUseCase {

    private final TransportGateway gateway;

    public SaveTransportUseCase(TransportGateway gateway) {
        this.gateway = gateway;
    }

    public Transport execute(SaveTransportCommand command) {
        if (gateway.existsTransportByCompanyIgnoreCaseAndDriverIgnoreCase(
                Contact.of(command.companyName(), command.companyNumber()),
                Contact.of(command.driverName(), command.driverNumber())
        ))
            throw new ConflictException("A empresa e o motorista do transporte não pode ser repetido");
        Transport transport = Transport.of(
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
