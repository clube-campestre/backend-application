package com.campestre.clube.backend_application.infrastructure.di;

import com.campestre.clube.backend_application.core.application.unit.ChangeUnitScoreUseCase;
import com.campestre.clube.backend_application.core.application.unit.GetUnitRankingUseCase;
import com.campestre.clube.backend_application.core.application.unit.ResetUnitScoresUseCase;
import com.campestre.clube.backend_application.core.application.unit.UpdateUnitScoreUseCase;
import com.campestre.clube.backend_application.infrastructure.persistence.jpa.unit.UnitJpaAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UnitBeanConfig {

    @Bean
    public ChangeUnitScoreUseCase changeUnitScoreUseCase(UnitJpaAdapter adapter) {
        return new ChangeUnitScoreUseCase(adapter);
    }

    @Bean
    public GetUnitRankingUseCase getUnitRankingUseCase(UnitJpaAdapter adapter) {
        return new GetUnitRankingUseCase(adapter);
    }

    @Bean
    public ResetUnitScoresUseCase resetUnitScoresUseCase(UnitJpaAdapter adapter) {
        return new ResetUnitScoresUseCase(adapter);
    }

    @Bean
    public UpdateUnitScoreUseCase updateUnitScoreUseCase(UnitJpaAdapter adapter) {
        return new UpdateUnitScoreUseCase(adapter);
    }
}
