package com.campestre.clube.backend_application.infrastructure.di;

import com.campestre.clube.backend_application.core.application.account.*;
import com.campestre.clube.backend_application.infrastructure.persistence.jpa.account.AccountJpaAdapter;
import com.campestre.clube.backend_application.infrastructure.security.BCryptPasswordHasher;
import com.campestre.clube.backend_application.infrastructure.security.JwtTokenManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountBeanConfig {

    @Bean
    public CreateAccountUseCase createAccountUseCase(
            AccountJpaAdapter adapter, BCryptPasswordHasher bCryptPasswordHasher
    ) {
        return new CreateAccountUseCase(adapter, bCryptPasswordHasher);
    }

    @Bean
    public UpdateAccountUseCase updateAccountUseCase(AccountJpaAdapter adapter) {
        return new UpdateAccountUseCase(adapter);
    }

    @Bean
    public DeleteAccountUseCase deleteAccountUseCase(AccountJpaAdapter adapter) {
        return new DeleteAccountUseCase(adapter);
    }

    @Bean
    public GetAccountByIdUseCase getAccountByIdUseCase(AccountJpaAdapter adapter) {
        return new GetAccountByIdUseCase(adapter);
    }

    @Bean
    public ListAccountUseCase listAccountUseCase(AccountJpaAdapter adapter) {
        return new ListAccountUseCase(adapter);
    }

    @Bean
    public AuthenticateAccountUseCase authenticateAccountUseCase(
            AccountJpaAdapter adapter, BCryptPasswordHasher bCryptPasswordHasher, JwtTokenManager jwtTokenManager
    ) {
        return new AuthenticateAccountUseCase(adapter, bCryptPasswordHasher, jwtTokenManager);
    }
}
