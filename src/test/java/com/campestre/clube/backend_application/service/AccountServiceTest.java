package com.campestre.clube.backend_application.service;

import com.campestre.clube.backend_application.config.JwtTokenManager;
import com.campestre.clube.backend_application.controller.dtos.responses.TokenAccountResponseDto;
import com.campestre.clube.backend_application.entity.Account;
import com.campestre.clube.backend_application.entity.enums.AccessTypeEnum;
import com.campestre.clube.backend_application.exceptions.BadRequestException;
import com.campestre.clube.backend_application.exceptions.ConflictException;
import com.campestre.clube.backend_application.exceptions.NotFoundException;
import com.campestre.clube.backend_application.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private JwtTokenManager jwtTokenManager;
    @Mock
    private AuthenticationManager authenticationManager;

    private Account account;

    @BeforeEach
    void setUp() {
        account = new Account();
        account.setId(1);
        account.setEmail("user@email.com");
        account.setPassword("1234");
        account.setName("User");
        account.setAccess(AccessTypeEnum.DIRETOR);
    }

    @Test
    @DisplayName("deve registrar Account com sucesso")
    void registerSuccessfully() {
        when(accountRepository.existsByEmail(account.getEmail())).thenReturn(false);
        when(passwordEncoder.encode("1234")).thenReturn("encoded");
        when(accountRepository.save(any())).thenReturn(account);

        Account result = accountService.register(account);

        assertEquals(account, result);
        verify(accountRepository).save(account);
    }

    @Test
    @DisplayName("deve lançar ConflictException ao registrar email já existente")
    void registerWithExistingEmail() {
        when(accountRepository.existsByEmail(account.getEmail())).thenReturn(true);

        assertThrows(ConflictException.class, () -> accountService.register(account));
        verify(accountRepository, never()).save(any());
    }

    @Test
    @DisplayName("deve autenticar Account com sucesso")
    void authenticateSuccessfully() {
        when(accountRepository.existsByEmail(account.getEmail())).thenReturn(true);
        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        when(accountRepository.findByEmail(account.getEmail())).thenReturn(account);
        when(jwtTokenManager.generateToken(authentication)).thenReturn("token");

        TokenAccountResponseDto dto = accountService.authenticate(account);

        assertEquals(account.getEmail(), dto.getEmail());
        assertEquals("token", dto.getToken());
    }

    @Test
    @DisplayName("deve lançar NotFoundException ao autenticar email não cadastrado")
    void authenticateNotFound() {
        when(accountRepository.existsByEmail(account.getEmail())).thenReturn(false);

        assertThrows(NotFoundException.class, () -> accountService.authenticate(account));
    }

    @Test
    @DisplayName("deve retornar todos os Accounts")
    void getAll() {
        List<Account> list = List.of(account);
        when(accountRepository.findAll()).thenReturn(list);

        assertEquals(list, accountService.getAll());
        verify(accountRepository).findAll();
    }

    @Test
    @DisplayName("deve buscar Account por id com sucesso")
    void getByIdSuccessfully() {
        when(accountRepository.existsById(1)).thenReturn(true);
        when(accountRepository.findById(1)).thenReturn(Optional.of(account));

        Account result = accountService.getById(1);

        assertEquals(account, result);
        verify(accountRepository).existsById(1);
        verify(accountRepository).findById(1);
    }

    @Test
    @DisplayName("deve lançar NotFoundException ao buscar Account por id inexistente")
    void getByIdNotFound() {
        when(accountRepository.existsById(1)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> accountService.getById(1));
        verify(accountRepository).existsById(1);
    }


    @Test
    @DisplayName("deve lançar NotFoundException ao atualizar Account inexistente")
    void updateNotFound() {
        when(accountRepository.existsById(1)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> accountService.update(1, account));
        verify(accountRepository, never()).save(any());
    }

    @Test
    @DisplayName("deve lançar ConflictException ao atualizar Account com email já existente")
    void updateWithExistingEmail() {
        when(accountRepository.existsById(1)).thenReturn(true);
        when(accountRepository.findById(1)).thenReturn(Optional.of(account));
        when(accountRepository.existsByEmailAndIdNot(account.getEmail(), 1)).thenReturn(true);

        assertThrows(ConflictException.class, () -> accountService.update(1, account));
        verify(accountRepository, never()).save(any());
    }

    @Test
    @DisplayName("deve deletar Account com sucesso")
    void deleteSuccessfully() {
        when(accountRepository.existsById(1)).thenReturn(true);
        doNothing().when(accountRepository).deleteById(1);

        accountService.delete(1);

        verify(accountRepository).deleteById(1);
    }

    @Test
    @DisplayName("deve lançar NotFoundException ao deletar Account inexistente")
    void deleteNotFound() {
        when(accountRepository.existsById(1)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> accountService.delete(1));
        verify(accountRepository, never()).deleteById(any());
    }
}