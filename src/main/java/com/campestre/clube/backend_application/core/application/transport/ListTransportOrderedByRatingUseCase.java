package com.campestre.clube.backend_application.core.application.transport;

import com.campestre.clube.backend_application.core.adapter.TransportGateway;
import com.campestre.clube.backend_application.core.domain.Transport;

import java.util.List;

public class ListTransportOrderedByRatingUseCase {

    private final TransportGateway gateway;

    public ListTransportOrderedByRatingUseCase(TransportGateway gateway) {
        this.gateway = gateway;
    }

    public List<Transport> execute() {
        return gateway.findOrderedByRatingDesc();
    }
}
