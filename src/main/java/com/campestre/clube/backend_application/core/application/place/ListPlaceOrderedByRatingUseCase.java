package com.campestre.clube.backend_application.core.application.place;

import com.campestre.clube.backend_application.core.adapter.PlaceGateway;
import com.campestre.clube.backend_application.core.domain.Place;

import java.util.List;

public class ListPlaceOrderedByRatingUseCase {

    private final PlaceGateway gateway;

    public ListPlaceOrderedByRatingUseCase(PlaceGateway gateway) {
        this.gateway = gateway;
    }

    public List<Place> execute() {
        return gateway.findOrderedByRatingDesc();
    }
}
