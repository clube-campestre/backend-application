package com.campestre.clube.backend_application.infrastructure.di;

import com.campestre.clube.backend_application.core.application.transport.*;
import com.campestre.clube.backend_application.infrastructure.persistence.jpa.transport.TransportJpaAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransportBeanConfig {

    @Bean
    public SaveTransportUseCase saveTransportUseCase(TransportJpaAdapter adapter) {
        return new SaveTransportUseCase(adapter);
    }

    @Bean
    public UpdateTransportUseCase updateTransportUseCase(TransportJpaAdapter adapter) {
        return new UpdateTransportUseCase(adapter);
    }

    @Bean
    public DeleteTransportUseCase deleteTransportUseCase(TransportJpaAdapter adapter) {
        return new DeleteTransportUseCase(adapter);
    }

    @Bean
    public GetTransportByIdUseCase getTransportByIdUseCase(TransportJpaAdapter adapter) {
        return new GetTransportByIdUseCase(adapter);
    }

    @Bean
    public ListTransportOrderedByRatingUseCase listTransportOrderedByRatingUseCase(TransportJpaAdapter adapter) {
        return new ListTransportOrderedByRatingUseCase(adapter);
    }
}
