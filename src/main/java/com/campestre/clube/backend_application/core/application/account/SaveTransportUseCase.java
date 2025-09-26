package com.campestre.clube.backend_application.core.application.account;

import com.campestre.clube.backend_application.core.adapter.TransportGateway;
import com.campestre.clube.backend_application.core.application.account.command.SaveTransportCommand;
import com.campestre.clube.backend_application.core.domain.Transport;
import com.campestre.clube.backend_application.core.domain.valueobject.Contact;

import static com.campestre.clube.backend_application.core.application.utils.ExceptionExtensions.CONFLICT_TRANSPORT_SAME_COMPANY_AND_DRIVER;

public class SaveTransportUseCase {

    private final TransportGateway gateway;

    public SaveTransportUseCase(TransportGateway gateway) {
        this.gateway = gateway;
    }

    public Transport execute(SaveTransportCommand command) {
        if (gateway.existsByCompanyIgnoreCaseAndDriverIgnoreCase(
                Contact.of(command.companyName(), command.companyNumber()),
                Contact.of(command.driverName(), command.driverNumber())
        )) throw CONFLICT_TRANSPORT_SAME_COMPANY_AND_DRIVER;

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
