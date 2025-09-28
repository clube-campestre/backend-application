package com.campestre.clube.backend_application.infrastructure.di;

import com.campestre.clube.backend_application.core.adapter.PasswordHasherGateway;
import com.campestre.clube.backend_application.core.adapter.TokenGeneratorGateway;
import com.campestre.clube.backend_application.core.application.account.*;
import com.campestre.clube.backend_application.infrastructure.persistence.jpa.account.AccountJpaAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountBeanConfig {

    @Bean
    public CreateAccountUseCase createAccountUseCase(AccountJpaAdapter adapter, PasswordHasherGateway passwordHasherGateway) {
        return new CreateAccountUseCase(adapter, passwordHasherGateway);
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
            AccountJpaAdapter adapter, PasswordHasherGateway passwordHasherGateway,
            TokenGeneratorGateway tokenGeneratorGateway
    ) {
        return new AuthenticateAccountUseCase(adapter, passwordHasherGateway, tokenGeneratorGateway);
    }
}
