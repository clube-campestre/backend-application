package com.campestre.clube.backend_application.core.application.place;

import com.campestre.clube.backend_application.core.adapter.PlaceGateway;
import com.campestre.clube.backend_application.core.application.exceptions.ConflictException;
import com.campestre.clube.backend_application.core.application.exceptions.NotFoundException;
import com.campestre.clube.backend_application.core.application.place.command.UpdatePlaceCommand;
import com.campestre.clube.backend_application.core.domain.Address;
import com.campestre.clube.backend_application.core.domain.Place;

import static com.campestre.clube.backend_application.core.application.extensions.ExceptionExtensions.CONFLICT_LOCAL_SAME_NAME;
import static com.campestre.clube.backend_application.core.application.extensions.ExceptionExtensions.NOT_FOUND_LOCAL;

public class UpdatePlaceUseCase {

    private final PlaceGateway gateway;

    public UpdatePlaceUseCase(PlaceGateway gateway) {
        this.gateway = gateway;
    }

    public Place execute(UpdatePlaceCommand command) {
        if (!gateway.existsById(command.id())) throw NOT_FOUND_LOCAL;
        if (gateway.existsByNameIgnoreCaseAndIdNot(command.name(), command.id())) throw CONFLICT_LOCAL_SAME_NAME;

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
