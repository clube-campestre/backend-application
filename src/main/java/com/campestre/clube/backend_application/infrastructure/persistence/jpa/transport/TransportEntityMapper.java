package com.campestre.clube.backend_application.infrastructure.persistence.jpa.transport;

import com.campestre.clube.backend_application.core.domain.Transport;

public class TransportEntityMapper {
    public static TransportEntity toEntity(Transport domain) {
        if (domain == null) return null;
        TransportEntity entity = new TransportEntity();
        entity.setId(domain.getId());
        entity.setPrice(domain.getPrice());
        entity.setTravelDistance(domain.getTravelDistance());
        entity.setCapacity(domain.getCapacity());
        entity.setCompanyName(domain.getCompany().getName());
        entity.setCompanyNumber(domain.getCompany().getCellphoneNumber().getNumber());
        entity.setDriverName(domain.getDriver().getName());
        entity.setDriverNumber(domain.getDriver().getCellphoneNumber().getNumber());
        entity.setRating(domain.getRating());
        return entity;
    }

    public static Transport toDomain(TransportEntity entity) {
        if (entity == null) return null;
        return Transport.of(
                entity.getId(),
                entity.getPrice(),
                entity.getTravelDistance(),
                entity.getCapacity(),
                entity.getCompanyName(),
                entity.getCompanyNumber(),
                entity.getDriverName(),
                entity.getDriverNumber(),
                entity.getRating()
        );
    }
}
