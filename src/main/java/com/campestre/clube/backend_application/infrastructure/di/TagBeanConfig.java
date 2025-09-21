package com.campestre.clube.backend_application.infrastructure.di;

import com.campestre.clube.backend_application.core.application.tag.*;
import com.campestre.clube.backend_application.infrastructure.persistence.jpa.tag.TagJpaAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TagBeanConfig {

    @Bean
    public SaveTagUseCase saveTagUseCase(TagJpaAdapter adapter) {
        return new SaveTagUseCase(adapter);
    }

    @Bean
    public UpdateTagUseCase updateTagUseCase(TagJpaAdapter adapter) {
        return new UpdateTagUseCase(adapter);
    }

    @Bean
    public DeleteTagUseCase deleteTagUseCase(TagJpaAdapter adapter) {
        return new DeleteTagUseCase(adapter);
    }

    @Bean
    public GetTagByIdUseCase getTagByIdUseCase(TagJpaAdapter adapter) {
        return new GetTagByIdUseCase(adapter);
    }

    @Bean
    public ListTagUseCase listTagUseCase(TagJpaAdapter adapter) {
        return new ListTagUseCase(adapter);
    }
}
