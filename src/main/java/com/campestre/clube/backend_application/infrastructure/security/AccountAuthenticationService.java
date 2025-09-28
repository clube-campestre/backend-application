package com.campestre.clube.backend_application.infrastructure.security;

import com.campestre.clube.backend_application.core.adapter.AccountGateway;
import com.campestre.clube.backend_application.core.domain.Account;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountAuthenticationService implements UserDetailsService {

    private final AccountGateway accountGateway;

    public AccountAuthenticationService(AccountGateway accountGateway) {
        this.accountGateway = accountGateway;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountGateway.findByEmail(email);

        if (account == null) {
            throw new UsernameNotFoundException("Conta não encontrada com email: " + email);
        }

        return User.builder()
                .username(account.getEmail().getValue())
                .password(account.getPassword())
                .roles("USER")
                .build();
    }
}
