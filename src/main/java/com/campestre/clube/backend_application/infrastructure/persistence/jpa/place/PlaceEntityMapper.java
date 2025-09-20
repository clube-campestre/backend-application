package com.campestre.clube.backend_application.infrastructure.persistence.jpa.place;

import com.campestre.clube.backend_application.core.domain.Place;
import com.campestre.clube.backend_application.infrastructure.persistence.jpa.address.AddressEntityMapper;

import java.util.List;
import java.util.stream.Collectors;

public class PlaceEntityMapper {
    public static PlaceEntity toEntity(Place domain) {
        if (domain == null) return null;
        PlaceEntity entity = new PlaceEntity();
        entity.setId(domain.getId());
        entity.setAddress(AddressEntityMapper.toEntity(domain.getAddress()));
        entity.setName(domain.getName());
        entity.setPrice(domain.getPrice());
        entity.setCapacity(domain.getCapacity());
        entity.setContactName(domain.getContact().getName());
        entity.setContactCellphoneNumber(domain.getContact().getCellphoneNumber().getNumber());
        entity.setRating(domain.getRating());
        return entity;
    }

    public static Place toDomain(PlaceEntity entity) {
        if (entity == null) return null;
        return Place.of(
                entity.getId(),
                AddressEntityMapper.toDomain(entity.getAddress()),
                entity.getName(),
                entity.getPrice(),
                entity.getCapacity(),
                entity.getContactName(),
                entity.getContactCellphoneNumber(),
                entity.getRating()
        );
    }

    public static List<Place> toDomain(List<PlaceEntity> entities) {
        if (entities == null) return null;
        return entities.stream().map(PlaceEntityMapper::toDomain).collect(Collectors.toList());
    }
}
