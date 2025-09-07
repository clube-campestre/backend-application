package com.campestre.clube.backend_application.infrastructure.di;

import com.campestre.clube.backend_application.core.application.transport.SaveTransportUseCase;
import com.campestre.clube.backend_application.infrastructure.persistence.jpa.transport.TransportJpaAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransportBeanConfig {

    @Bean
    public SaveTransportUseCase saveTransportUseCase(TransportJpaAdapter adapter) {
        return new SaveTransportUseCase(adapter);
    }
}
