package com.campestre.clube.backend_application.service;

import com.campestre.clube.backend_application.model.Account;
import com.campestre.clube.backend_application.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Boolean login(String email, String password) {
        return accountRepository.findByEmailAndPassword(email, password) != null;
    }

    public Account save(Account account) {
        return accountRepository.save(account);
    }

    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    public Account getById(String id) {
        return accountRepository.findById(id).orElse(null);
    }
}
