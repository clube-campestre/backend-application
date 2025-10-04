package com.campestre.clube.backend_application.core.application.place;

import com.campestre.clube.backend_application.core.adapter.HasherGateway;
import com.campestre.clube.backend_application.core.adapter.PlaceGateway;
import com.campestre.clube.backend_application.core.application.place.command.GetPlaceByIdCommand;
import com.campestre.clube.backend_application.core.domain.Place;

import static com.campestre.clube.backend_application.core.application.utils.ExceptionExtensions.NOT_FOUND_LOCAL;

public class GetPlaceByIdUseCase {

    private final PlaceGateway gateway;
    private final HasherGateway hasherGateway;

    public GetPlaceByIdUseCase(PlaceGateway gateway, HasherGateway hasherGateway) {
        this.gateway = gateway;
        this.hasherGateway = hasherGateway;
    }

    public Place execute(GetPlaceByIdCommand command) {
        if (!gateway.existsById(command.id())) throw NOT_FOUND_LOCAL;

        Place place = gateway.findById(command.id());
        place.getAddress().setHouseNumber(hasherGateway.decrypt(place.getAddress().getHouseNumber()));
        place.getAddress().setReferenceHouse(hasherGateway.decrypt(place.getAddress().getReferenceHouse()));

        return place;
    }
}
