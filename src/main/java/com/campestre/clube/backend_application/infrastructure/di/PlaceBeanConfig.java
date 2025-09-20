package com.campestre.clube.backend_application.infrastructure.di;

import com.campestre.clube.backend_application.core.application.place.*;
import com.campestre.clube.backend_application.infrastructure.persistence.jpa.place.PlaceJpaAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlaceBeanConfig {

    @Bean
    public SavePlaceUseCase savePlaceUseCase(PlaceJpaAdapter adapter) {
        return new SavePlaceUseCase(adapter);
    }

    @Bean
    public UpdatePlaceUseCase updatePlaceUseCase(PlaceJpaAdapter adapter) {
        return new UpdatePlaceUseCase(adapter);
    }

    @Bean
    public DeletePlaceUseCase deletePlaceUseCase(PlaceJpaAdapter adapter) {
        return new DeletePlaceUseCase(adapter);
    }

    @Bean
    public GetPlaceByIdUseCase getPlaceByIdUseCase(PlaceJpaAdapter adapter) {
        return new GetPlaceByIdUseCase(adapter);
    }

    @Bean
    public ListPlaceOrderedByRatingUseCase listPlaceOrderedByRatingUseCase(PlaceJpaAdapter adapter) {
        return new ListPlaceOrderedByRatingUseCase(adapter);
    }
}
