package com.campestre.clube.backend_application.core.application.place;

import com.campestre.clube.backend_application.core.adapter.HasherGateway;
import com.campestre.clube.backend_application.core.adapter.PlaceGateway;
import com.campestre.clube.backend_application.core.domain.Place;

import java.util.List;

public class ListPlaceOrderedByRatingUseCase {

    private final PlaceGateway gateway;
    private final HasherGateway hasherGateway;

    public ListPlaceOrderedByRatingUseCase(PlaceGateway gateway, HasherGateway hasherGateway) {
        this.gateway = gateway;
        this.hasherGateway = hasherGateway;
    }

    public List<Place> execute() {
        return gateway.findOrderedByRatingDesc().stream().map(place -> {
            place.getAddress().setHouseNumber(hasherGateway.decrypt(place.getAddress().getHouseNumber()));
            place.getAddress().setReferenceHouse(hasherGateway.decrypt(place.getAddress().getReferenceHouse()));
            return place;
        }).toList();
    }
}
