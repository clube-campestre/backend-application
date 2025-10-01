package com.campestre.clube.backend_application.infrastructure.di;

import com.campestre.clube.backend_application.core.application.goal.GetGoalByTagIdUseCase;
import com.campestre.clube.backend_application.core.application.statement.*;
import com.campestre.clube.backend_application.infrastructure.persistence.jpa.statement.StatementJpaAdapter;
import com.campestre.clube.backend_application.infrastructure.persistence.jpa.tag.TagJpaAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StatementBeanConfig {

    @Bean
    public SaveStatementUseCase saveStatementUseCase(StatementJpaAdapter adapter, TagJpaAdapter tagJpaAdapter) {
        return new SaveStatementUseCase(adapter, tagJpaAdapter);
    }

    @Bean
    public UpdateStatementUseCase updateStatementUseCase(StatementJpaAdapter adapter, TagJpaAdapter tagJpaAdapter) {
        return new UpdateStatementUseCase(adapter, tagJpaAdapter);
    }

    @Bean
    public DeleteStatementByIdUseCase deleteStatementByIdUseCase(StatementJpaAdapter adapter) {
        return new DeleteStatementByIdUseCase(adapter);
    }

    @Bean
    public DeleteStatementByTagSurnameUseCase deleteStatementByTagSurnameUseCase(StatementJpaAdapter adapter) {
        return new DeleteStatementByTagSurnameUseCase(adapter);
    }

    @Bean
    public GetStatementByIdUseCase getStatementByIdUseCase(StatementJpaAdapter adapter) {
        return new GetStatementByIdUseCase(adapter);
    }

    @Bean
    public GetGoalByTagIdUseCase getGoalByTagIdUseCase(StatementJpaAdapter adapter, TagJpaAdapter tagAdapter) {
        return new GetGoalByTagIdUseCase(adapter, tagAdapter);
    }

    @Bean
    public ListStatementByFilterAndPaginationUseCase listStatementByFilterAndPaginationUseCase(
            StatementJpaAdapter adapter
    ) {
        return new ListStatementByFilterAndPaginationUseCase(adapter);
    }
}
