package com.campestre.clube.backend_application.core.application.place;

import com.campestre.clube.backend_application.core.adapter.PlaceGateway;
import com.campestre.clube.backend_application.core.application.exceptions.ConflictException;
import com.campestre.clube.backend_application.core.application.exceptions.NotFoundException;
import com.campestre.clube.backend_application.core.application.place.command.UpdatePlaceCommand;
import com.campestre.clube.backend_application.core.domain.Address;
import com.campestre.clube.backend_application.core.domain.Place;

public class UpdatePlaceUseCase {

    private final PlaceGateway gateway;

    public UpdatePlaceUseCase(PlaceGateway gateway) {
        this.gateway = gateway;
    }

    public Place execute(UpdatePlaceCommand command) {
        if (gateway.existsById(command.id()))
            throw new NotFoundException("O local não foi encontrado");

        if (gateway.existsByNameIgnoreCaseAndIdNot(command.name(), command.id()))
            throw new ConflictException("O nome ou o endereço do local não pode ser repetido");

        Place place = Place.of(
                command.id(),
                Address.of(
                        command.addressId(),
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
