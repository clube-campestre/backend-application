package com.campestre.clube.backend_application.core.application.place;

import com.campestre.clube.backend_application.core.adapter.PlaceGateway;
import com.campestre.clube.backend_application.core.application.exceptions.ConflictException;
import com.campestre.clube.backend_application.core.application.place.command.SavePlaceCommand;
import com.campestre.clube.backend_application.core.domain.Address;
import com.campestre.clube.backend_application.core.domain.Place;

public class SavePlaceUseCase {

    private final PlaceGateway gateway;

    public SavePlaceUseCase(PlaceGateway gateway) {
        this.gateway = gateway;
    }

    public Place execute(SavePlaceCommand command) {
        if (gateway.existsByNameIgnoreCase(command.name()))
            throw new ConflictException("O nome do local não pode ser repetido");

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
