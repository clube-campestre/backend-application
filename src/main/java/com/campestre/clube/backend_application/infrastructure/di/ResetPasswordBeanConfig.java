package com.campestre.clube.backend_application.infrastructure.di;

import com.campestre.clube.backend_application.core.application.resetpassword.GenerateResetPasswordCodeUseCase;
import com.campestre.clube.backend_application.core.application.resetpassword.ResetPasswordUseCase;
import com.campestre.clube.backend_application.core.application.resetpassword.ValidateResetPasswordCodeUseCase;
import com.campestre.clube.backend_application.infrastructure.persistence.jpa.account.AccountJpaAdapter;
import com.campestre.clube.backend_application.infrastructure.persistence.jpa.resetpassword.ResetPasswordJpaAdapter;
import com.campestre.clube.backend_application.infrastructure.security.BCryptPasswordHasher;
import com.campestre.clube.backend_application.infrastructure.security.NotificationSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResetPasswordBeanConfig {

    @Bean
    public GenerateResetPasswordCodeUseCase generateResetPasswordCodeUseCase(
            ResetPasswordJpaAdapter adapter, NotificationSender notificationSender, AccountJpaAdapter accountAdapter
    ) {
        return new GenerateResetPasswordCodeUseCase(adapter, notificationSender, accountAdapter);
    }

    @Bean
    public ResetPasswordUseCase resetPasswordUseCase(
            ResetPasswordJpaAdapter adapter, BCryptPasswordHasher bCryptPasswordHasher, AccountJpaAdapter accountAdapter
    ) {
        return new ResetPasswordUseCase(adapter, bCryptPasswordHasher, accountAdapter);
    }

    @Bean
    public ValidateResetPasswordCodeUseCase validateResetPasswordCodeUseCase(ResetPasswordJpaAdapter adapter) {
        return new ValidateResetPasswordCodeUseCase(adapter);
    }
}
