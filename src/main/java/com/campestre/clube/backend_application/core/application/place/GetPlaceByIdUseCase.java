package com.campestre.clube.backend_application.core.application.place;

import com.campestre.clube.backend_application.core.adapter.PlaceGateway;
import com.campestre.clube.backend_application.core.application.place.command.GetPlaceByIdCommand;
import com.campestre.clube.backend_application.core.domain.Place;

import static com.campestre.clube.backend_application.core.application.utils.ExceptionExtensions.NOT_FOUND_LOCAL;

public class GetPlaceByIdUseCase {

    private final PlaceGateway gateway;

    public GetPlaceByIdUseCase(PlaceGateway gateway) {
        this.gateway = gateway;
    }

    public Place execute(GetPlaceByIdCommand command) {
        if (!gateway.existsById(command.id())) throw NOT_FOUND_LOCAL;
        return gateway.findById(command.id());
    }
}
