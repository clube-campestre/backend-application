package com.campestre.clube.backend_application.service;

import com.campestre.clube.backend_application.controller.dtos.TokenAccountRequestDto;
import com.campestre.clube.backend_application.exceptions.BadRequestException;
import com.campestre.clube.backend_application.exceptions.ConflictException;
import com.campestre.clube.backend_application.exceptions.NotFoundException;
import com.campestre.clube.backend_application.entity.Account;
import com.campestre.clube.backend_application.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private GerenciadorTokenJwt gerenciadorTokenJwt;

    @Autowired
    private AuthenticationManager authenticationManager;

    public Account login(Account accountRequest) {
        if (!accountRepository.existsByEmailAndPassword(accountRequest.getEmail(), accountRequest.getPassword()))
            throw new BadRequestException("Incorrect email or password");

        return accountRepository.findByEmailAndPassword(accountRequest.getEmail(), accountRequest.getPassword());
    }

//    public AuthenticationResponse authenticate(AuthenticationRequest request) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getEmail(),
//                        request.getPassword()
//                )
//        );
//        var user = repository.findByEmail(request.getEmail())
//                .orElseThrow();
//        var jwtToken = jwtService.generateToken(user);
//        var refreshToken = jwtService.generateRefreshToken(user);
//        revokeAllUserTokens(user);
//        saveUserToken(user, jwtToken);
//        return AuthenticationResponse.builder()
//                .accessToken(jwtToken)
//                .refreshToken(refreshToken)
//                .build();
//    }

    public Account register(Account account) {
        if (accountRepository.existsByEmailOrCpf(account.getEmail(), account.getCpf()))
            throw new ConflictException("User with existing email or CPF");
        if (!validateEmail(account.getEmail()))
            throw new BadRequestException("Invalid email");
        if (!validateCpf(account.getCpf()))
            throw new BadRequestException("Invalid CPF");
        account.setPassword(passwordEncoder.encode(account.getPassword()));

        return accountRepository.save(account);
    }

    public TokenAccountRequestDto autenticar(Account account) {
        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                account.getEmail(), account.getPassword());

        final Authentication authentication = this


    }

    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    public Account getById(Integer id) {
        Optional<Account> account = accountRepository.findById(id);
        userNotFoundValidation(account, id);

        return account.get();
    }

    public Account update(Integer id, Account newAccount){
        Optional<Account> account = accountRepository.findById(id);

        userNotFoundValidation(account, id);
        if (accountRepository.existsByEmailAndIdNot(newAccount.getEmail(), id))
            throw new ConflictException("User with existing email");
        if (accountRepository.existsByCpfAndIdNot(newAccount.getCpf(), id))
            throw new ConflictException("User with existing CPF");

        newAccount.setId(id);
        return accountRepository.save(newAccount);
    }

    public void delete(Integer id){
        if(!accountRepository.existsById(id))
            throw new NotFoundException("User by id [%s] not found".formatted(id));

        accountRepository.deleteById(id);
    }



    private Boolean validateEmail(String email) {
        return email.contains(".") && email.contains("@");
    }
    private Boolean validateCpf(String cpf) { return cpf.length() == 11;}

    private void userNotFoundValidation(Optional<Account> account, Integer id) {
        if (account.isEmpty())
            throw new NotFoundException("User by id [%s] not found".formatted(id));
    }
}
