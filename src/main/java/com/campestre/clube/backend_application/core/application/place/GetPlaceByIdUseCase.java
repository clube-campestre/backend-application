package com.campestre.clube.backend_application.core.application.place;

import com.campestre.clube.backend_application.core.adapter.PlaceGateway;
import com.campestre.clube.backend_application.core.application.exceptions.NotFoundException;
import com.campestre.clube.backend_application.core.application.place.command.GetPlaceByIdCommand;
import com.campestre.clube.backend_application.core.domain.Place;

public class GetPlaceByIdUseCase {

    private final PlaceGateway gateway;

    public GetPlaceByIdUseCase(PlaceGateway gateway) {
        this.gateway = gateway;
    }

    public Place execute(GetPlaceByIdCommand command) {
        if (gateway.existsById(command.id()))
            throw new NotFoundException("O local não foi encontrado");
        return gateway.findById(command.id());
    }
}
