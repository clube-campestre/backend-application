package com.campestre.clube.backend_application.service;

import com.campestre.clube.backend_application.config.JwtTokenManager;
import com.campestre.clube.backend_application.controller.dtos.responses.TokenAccountResponseDto;
import com.campestre.clube.backend_application.controller.mapper.AccountMapper;
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
            throw new ConflictException("Já existe uma conta cadastrada com este e-mail.");
        account.setPassword(passwordEncoder.encode(account.getPassword()));

        return accountRepository.save(account);
    }

    public TokenAccountResponseDto authenticate(Account account) {
        if (!accountRepository.existsByEmail(account.getEmail()))
            throw new NotFoundException("Não encontramos uma conta com o e-mail informado.");

        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                account.getEmail(), account.getPassword());

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Account AuthenticateAccount = accountRepository.findByEmail(account.getEmail());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = jwtTokenManager.generateToken(authentication);

        return AccountMapper.of(AuthenticateAccount, token);
    }

    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    public Account getById(Integer id) {
        existsByIdOrThrow(id);
        return accountRepository.findById(id).get();
    }

    public Account update(Integer id, Account newAccount){
        existsByIdOrThrow(id);
        Optional<Account> account = accountRepository.findById(id);

        if (accountRepository.existsByEmailAndIdNot(newAccount.getEmail(), id))
            throw new ConflictException(
                    "Não é possível atualizar. O e-mail informado já está cadastrado em outra conta."
            );

        account.get().setName(newAccount.getName());
        account.get().setEmail(newAccount.getEmail());
        account.get().setAccess(newAccount.getAccess());
        return accountRepository.save(account.get());
    }

    public void delete(Integer id){
        existsByIdOrThrow(id);

        accountRepository.deleteById(id);
    }



    private void existsByIdOrThrow(Integer id) {
        if (!accountRepository.existsById(id))
            throw new NotFoundException("Não encontramos o usuário solicitado.");
    }
}
