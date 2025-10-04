package com.campestre.clube.backend_application.core.application.place;

import com.campestre.clube.backend_application.core.adapter.HasherGateway;
import com.campestre.clube.backend_application.core.adapter.PlaceGateway;
import com.campestre.clube.backend_application.core.application.place.command.SavePlaceCommand;
import com.campestre.clube.backend_application.core.domain.Address;
import com.campestre.clube.backend_application.core.domain.Place;

import static com.campestre.clube.backend_application.core.exceptions.ExceptionExtensions.CONFLICT_LOCAL_SAME_NAME;

public class SavePlaceUseCase {

    private final PlaceGateway gateway;
    private final HasherGateway hasherGateway;

    public SavePlaceUseCase(PlaceGateway gateway, HasherGateway hasherGateway) {
        this.gateway = gateway;
        this.hasherGateway = hasherGateway;
    }

    public Place execute(SavePlaceCommand command) {
        String addressHouseNumberHash = hasherGateway.encrypt(command.addressHouseNumber());
        String addressReferenceHouseHash = hasherGateway.encrypt(command.addressReferenceHouse());

        if (gateway.existsByNameIgnoreCase(command.name())) throw CONFLICT_LOCAL_SAME_NAME;

        Place place = Place.of(
                Address.of(
                        command.addressStreet(),
                        addressHouseNumberHash,
                        command.addressDistrict(),
                        command.addressState(),
                        command.addressCity(),
                        command.addressCepNumber(),
                        addressReferenceHouseHash
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
