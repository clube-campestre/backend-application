package com.campestre.clube.backend_application.service;

import com.campestre.clube.backend_application.exceptions.EmailConfictException;
import com.campestre.clube.backend_application.exceptions.LoginBadRequestException;
import com.campestre.clube.backend_application.exceptions.UserNotFoundException;
import com.campestre.clube.backend_application.model.Account;
import com.campestre.clube.backend_application.model.AccountRequest;
import com.campestre.clube.backend_application.repository.AccountRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public String login(AccountRequest account) {
        if (!accountRepository.existsByEmailAndPassword(account.getEmail(), account.getPassword()))
            throw new LoginBadRequestException();

        return "Authenticated user";
    }

    public Account register(Account account) throws BadRequestException {
        if (accountRepository.existsByEmail(account.getEmail()))
            throw new EmailConfictException();
        if (!validateEmail(account.getEmail()))
            throw new BadRequestException("Invalid email");

        return accountRepository.save(account);
    }

    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    public Account getById(Integer id) {
        Optional<Account> account = accountRepository.findById(id);
        userNotFoundValidation(account, id);

        return account.get();
    }

    public Account update(Integer id, AccountRequest newAccount){
        Optional<Account> account = accountRepository.findById(id);

        userNotFoundValidation(account, id);
        if (!accountRepository.existsByEmailAndIdNot(newAccount.getEmail(), id))
            throw new EmailConfictException();

        account.get().setEmail(newAccount.getEmail());
        account.get().setPassword(newAccount.getPassword());
        return accountRepository.save(account.get());
    }

    public void delete(Integer id){
        if(!accountRepository.existsById(id))
            throw new UserNotFoundException(id);

        accountRepository.deleteById(id);
    }



    private Boolean validateEmail(String email) {
        return email.contains(".") && email.contains("@");
    }

    private void userNotFoundValidation(Optional<Account> account, Integer id) {
        if (account.isEmpty())
            throw new UserNotFoundException(id);
    }
}
