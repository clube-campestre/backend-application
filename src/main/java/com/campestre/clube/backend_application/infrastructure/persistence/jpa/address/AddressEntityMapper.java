package com.campestre.clube.backend_application.infrastructure.persistence.jpa.address;

import com.campestre.clube.backend_application.core.domain.Address;
import com.campestre.clube.backend_application.core.domain.Place;
import com.campestre.clube.backend_application.core.domain.valueobject.Contact;
import com.campestre.clube.backend_application.infrastructure.persistence.jpa.place.PlaceEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class AddressEntityMapper {
    public static AddressEntity toEntity(Address domain) {
        if (domain == null) return null;
        AddressEntity entity = new AddressEntity();
        entity.setId(domain.getId());
        entity.setStreet(domain.getStreet());
        entity.setHouseNumber(domain.getHouseNumber());
        entity.setDistrict(domain.getDistrict());
        entity.setState(domain.getState());
        entity.setCity(domain.getCity());
        entity.setCep(domain.getCep().getNumber());
        entity.setReferenceHouse(domain.getReferenceHouse());
        return entity;
    }

    public static Address toDomain(AddressEntity entity) {
        if (entity == null) return null;
        return Address.of(
                entity.getId(),
                entity.getStreet(),
                entity.getHouseNumber(),
                entity.getDistrict(),
                entity.getState(),
                entity.getCity(),
                entity.getCep(),
                entity.getReferenceHouse()
        );
    }
}
