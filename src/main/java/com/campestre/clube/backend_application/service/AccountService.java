package com.campestre.clube.backend_application.service;

import com.campestre.clube.backend_application.model.Account;
import com.campestre.clube.backend_application.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Account update(String id,Account newAccountData){
        Optional<Account> existingAccount = accountRepository.findById(id);

        if(existingAccount.isPresent()){
            Account account = existingAccount.get();
            account.setEmail(newAccountData.getEmail());
            account.setPassword(newAccountData.getPassword());
            return accountRepository.save(account);
        }

        return null;
    }


    public Boolean delete(String id){
        if(accountRepository.existsById(id)){
            accountRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
