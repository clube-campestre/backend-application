package com.campestre.clube.backend_application.core.application.place;

import com.campestre.clube.backend_application.core.adapter.PlaceGateway;
import com.campestre.clube.backend_application.core.application.place.command.DeletePlaceCommand;

import static com.campestre.clube.backend_application.core.exceptions.ExceptionExtensions.NOT_FOUND_LOCAL;

public class DeletePlaceUseCase {

    private final PlaceGateway gateway;

    public DeletePlaceUseCase(PlaceGateway gateway) {
        this.gateway = gateway;
    }

    public void execute(DeletePlaceCommand command) {
        if (!gateway.existsById(command.id())) throw NOT_FOUND_LOCAL;
        gateway.removeById(command.id());
    }
}
