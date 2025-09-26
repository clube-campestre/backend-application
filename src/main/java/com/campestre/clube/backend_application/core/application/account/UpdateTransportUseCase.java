package com.campestre.clube.backend_application.core.application.account;

import com.campestre.clube.backend_application.core.adapter.TransportGateway;
import com.campestre.clube.backend_application.core.application.account.command.UpdateTransportCommand;
import com.campestre.clube.backend_application.core.domain.Transport;
import com.campestre.clube.backend_application.core.domain.valueobject.Contact;

import static com.campestre.clube.backend_application.core.application.utils.ExceptionExtensions.CONFLICT_TRANSPORT_SAME_COMPANY_AND_DRIVER;
import static com.campestre.clube.backend_application.core.application.utils.ExceptionExtensions.NOT_FOUND_TRANSPORT;

public class UpdateTransportUseCase {

    private final TransportGateway gateway;

    public UpdateTransportUseCase(TransportGateway gateway) {
        this.gateway = gateway;
    }

    public Transport execute(UpdateTransportCommand command) {
        if (!gateway.existsById(command.id())) throw NOT_FOUND_TRANSPORT;
        if (gateway.existsByCompanyIgnoreCaseAndDriverIgnoreCaseAndIdNot(
                Contact.of(command.companyName(), command.companyNumber()),
                Contact.of(command.driverName(), command.driverNumber()),
                command.id()
        )) throw CONFLICT_TRANSPORT_SAME_COMPANY_AND_DRIVER;

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
