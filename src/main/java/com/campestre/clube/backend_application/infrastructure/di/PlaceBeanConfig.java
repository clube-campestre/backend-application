package com.campestre.clube.backend_application.infrastructure.di;

import com.campestre.clube.backend_application.core.adapter.HasherGateway;
import com.campestre.clube.backend_application.core.application.place.*;
import com.campestre.clube.backend_application.infrastructure.persistence.jpa.place.PlaceJpaAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlaceBeanConfig {

    @Bean
    public SavePlaceUseCase savePlaceUseCase(PlaceJpaAdapter adapter, HasherGateway hasherGateway) {
        return new SavePlaceUseCase(adapter, hasherGateway);
    }

    @Bean
    public UpdatePlaceUseCase updatePlaceUseCase(PlaceJpaAdapter adapter, HasherGateway hasherGateway) {
        return new UpdatePlaceUseCase(adapter, hasherGateway);
    }

    @Bean
    public DeletePlaceUseCase deletePlaceUseCase(PlaceJpaAdapter adapter) {
        return new DeletePlaceUseCase(adapter);
    }

    @Bean
    public GetPlaceByIdUseCase getPlaceByIdUseCase(PlaceJpaAdapter adapter, HasherGateway hasherGateway) {
        return new GetPlaceByIdUseCase(adapter, hasherGateway);
    }

    @Bean
    public ListPlaceOrderedByRatingUseCase listPlaceOrderedByRatingUseCase(
            PlaceJpaAdapter adapter, HasherGateway hasherGateway
    ) {
        return new ListPlaceOrderedByRatingUseCase(adapter, hasherGateway);
    }
}
