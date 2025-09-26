package com.campestre.clube.backend_application.core.application.place;

import com.campestre.clube.backend_application.core.adapter.PlaceGateway;
import com.campestre.clube.backend_application.core.application.place.command.SavePlaceCommand;
import com.campestre.clube.backend_application.core.domain.Address;
import com.campestre.clube.backend_application.core.domain.Place;

import static com.campestre.clube.backend_application.core.application.utils.ExceptionExtensions.CONFLICT_LOCAL_SAME_NAME;

public class SavePlaceUseCase {

    private final PlaceGateway gateway;

    public SavePlaceUseCase(PlaceGateway gateway) {
        this.gateway = gateway;
    }

    public Place execute(SavePlaceCommand command) {
        if (gateway.existsByNameIgnoreCase(command.name())) throw CONFLICT_LOCAL_SAME_NAME;

        Place place = Place.of(
                Address.of(
                        command.addressStreet(),
                        command.addressHouseNumber(),
                        command.addressDistrict(),
                        command.addressState(),
                        command.addressCity(),
                        command.addressCepNumber(),
                        command.addressReferenceHouse()
                ),
                command.name(),
                command.price(),
                command.capacity(),
                command.contactName(),
                command.contactCellphoneNumber(),
                command.rating()
        );

        return gateway.save(place);
    }
}
