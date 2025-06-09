package com.campestre.clube.backend_application.service;

import com.campestre.clube.backend_application.controller.dtos.DetailsAccountDto;
import com.campestre.clube.backend_application.entity.Account;
import com.campestre.clube.backend_application.exceptions.NotFoundException;
import com.campestre.clube.backend_application.repository.AccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountAuthenticationServiceTest {

    @InjectMocks
    private AccountAuthenticationService service;
    @Mock
    private AccountRepository accountRepository;

    @Test
    @DisplayName("deve retornar UserDetails ao encontrar email")
    void loadUserByUsernameSuccess() {
        String email = "user@email.com";
        Account account = new Account();
        when(accountRepository.existsByEmail(email)).thenReturn(true);
        when(accountRepository.findByEmail(email)).thenReturn(account);

        UserDetails result = service.loadUserByUsername(email);

        assertNotNull(result);
        assertTrue(result instanceof DetailsAccountDto);
        verify(accountRepository).existsByEmail(email);
        verify(accountRepository).findByEmail(email);
    }

    @Test
    @DisplayName("deve lançar NotFoundException se email não existir")
    void loadUserByUsernameNotFound() {
        String email = "notfound@email.com";
        when(accountRepository.existsByEmail(email)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> service.loadUserByUsername(email));
        verify(accountRepository).existsByEmail(email);
        verify(accountRepository, never()).findByEmail(any());
    }
}