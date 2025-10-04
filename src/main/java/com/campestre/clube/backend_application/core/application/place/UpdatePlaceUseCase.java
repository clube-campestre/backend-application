package com.campestre.clube.backend_application.core.application.place;

import com.campestre.clube.backend_application.core.adapter.HasherGateway;
import com.campestre.clube.backend_application.core.adapter.PlaceGateway;
import com.campestre.clube.backend_application.core.application.place.command.UpdatePlaceCommand;
import com.campestre.clube.backend_application.core.domain.Address;
import com.campestre.clube.backend_application.core.domain.Place;

import static com.campestre.clube.backend_application.core.application.utils.ExceptionExtensions.CONFLICT_LOCAL_SAME_NAME;
import static com.campestre.clube.backend_application.core.application.utils.ExceptionExtensions.NOT_FOUND_LOCAL;

public class UpdatePlaceUseCase {

    private final PlaceGateway gateway;
    private final HasherGateway hasherGateway;

    public UpdatePlaceUseCase(PlaceGateway gateway, HasherGateway hasherGateway) {
        this.gateway = gateway;
        this.hasherGateway = hasherGateway;
    }

    public Place execute(UpdatePlaceCommand command) {
        String addressHouseNumberHash = hasherGateway.crypt(command.addressHouseNumber());
        String addressReferenceHouseHash = hasherGateway.crypt(command.addressReferenceHouse());

        if (!gateway.existsById(command.id())) throw NOT_FOUND_LOCAL;
        if (gateway.existsByNameIgnoreCaseAndIdNot(command.name(), command.id())) throw CONFLICT_LOCAL_SAME_NAME;

        Place place = Place.of(
                command.id(),
                Address.of(
                        command.addressId(),
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
