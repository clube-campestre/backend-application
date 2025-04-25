package com.campestre.clube.backend_application.service;

import com.campestre.clube.backend_application.config.JwtTokenManager;
import com.campestre.clube.backend_application.controller.dtos.TokenAccountDto;
import com.campestre.clube.backend_application.controller.mapper.AccountMapper;
import com.campestre.clube.backend_application.exceptions.BadRequestException;
import com.campestre.clube.backend_application.exceptions.ConflictException;
import com.campestre.clube.backend_application.exceptions.NotFoundException;
import com.campestre.clube.backend_application.entity.Account;
import com.campestre.clube.backend_application.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private JwtTokenManager jwtTokenManager;

    @Autowired
    private AuthenticationManager authenticationManager;

    public Account register(Account account) {
        if (accountRepository.existsByEmail(account.getEmail()))
            throw new ConflictException("User with existing email");
        if (!validateEmail(account.getEmail()))
            throw new BadRequestException("Invalid email");
        account.setPassword(passwordEncoder.encode(account.getPassword()));

        return accountRepository.save(account);
    }

    public TokenAccountDto authenticate(Account account) {
        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                account.getEmail(), account.getPassword());

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        if (!accountRepository.existsByEmail(account.getEmail()))
            throw new NotFoundException("Account email not registered");
        Account AuthenticateAccount = accountRepository.findByEmail(account.getEmail());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = jwtTokenManager.generateToken(authentication);

        return AccountMapper.of(AuthenticateAccount, token);
    }



    public Account login(Account accountRequest) {
        if (!accountRepository.existsByEmailAndPassword(accountRequest.getEmail(), accountRequest.getPassword()))
            throw new BadRequestException("Incorrect email or password");

        return accountRepository.findByEmailAndPassword(accountRequest.getEmail(), accountRequest.getPassword());
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

    private void userNotFoundValidation(Optional<Account> account, Integer id) {
        if (account.isEmpty())
            throw new NotFoundException("User by id [%s] not found".formatted(id));
    }
}
