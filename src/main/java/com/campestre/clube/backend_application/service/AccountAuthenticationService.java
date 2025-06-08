package com.campestre.clube.backend_application.service;

import com.campestre.clube.backend_application.controller.dtos.DetailsAccountDto;
import com.campestre.clube.backend_application.exceptions.NotFoundException;
import com.campestre.clube.backend_application.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountAuthenticationService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (!accountRepository.existsByEmail(email))
            throw new NotFoundException("Usuário com o e-mail informado não foi encontrado.");
        return new DetailsAccountDto(accountRepository.findByEmail(email));
    }
}
