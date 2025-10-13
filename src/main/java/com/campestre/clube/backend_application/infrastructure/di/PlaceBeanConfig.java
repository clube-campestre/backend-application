package com.campestre.clube.backend_application.infrastructure.di;

import com.campestre.clube.backend_application.core.application.place.*;
import com.campestre.clube.backend_application.infrastructure.persistence.jpa.place.PlaceJpaAdapter;
import com.campestre.clube.backend_application.infrastructure.security.JasyptHasher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlaceBeanConfig {

    @Bean
    public SavePlaceUseCase savePlaceUseCase(PlaceJpaAdapter adapter, JasyptHasher hasher) {
        return new SavePlaceUseCase(adapter, hasher);
    }

    @Bean
    public UpdatePlaceUseCase updatePlaceUseCase(PlaceJpaAdapter adapter, JasyptHasher hasher) {
        return new UpdatePlaceUseCase(adapter, hasher);
    }

    @Bean
    public DeletePlaceUseCase deletePlaceUseCase(PlaceJpaAdapter adapter) {
        return new DeletePlaceUseCase(adapter);
    }

    @Bean
    public GetPlaceByIdUseCase getPlaceByIdUseCase(PlaceJpaAdapter adapter, JasyptHasher hasher) {
        return new GetPlaceByIdUseCase(adapter, hasher);
    }

    @Bean
    public ListPlaceOrderedByRatingUseCase listPlaceOrderedByRatingUseCase(
            PlaceJpaAdapter adapter, JasyptHasher hasher
    ) {
        return new ListPlaceOrderedByRatingUseCase(adapter, hasher);
    }
}
