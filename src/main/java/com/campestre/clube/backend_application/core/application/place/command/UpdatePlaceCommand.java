package com.campestre.clube.backend_application.core.application.place.command;

import java.math.BigDecimal;

public record UpdatePlaceCommand(
        Integer id,
        String name,
        BigDecimal price,
        Integer capacity,
        String contactName,
        String contactCellphoneNumber,
        Integer rating,
        Integer addressId,
        String addressStreet,
        String addressHouseNumber,
        String addressDistrict,
        String addressState,
        String addressCity,
        String addressCepNumber,
        String addressReferenceHouse
){}