package com.campestre.clube.backend_application.service;

import com.campestre.clube.backend_application.exceptions.BadRequestException;
import com.campestre.clube.backend_application.exceptions.ConflictException;
import com.campestre.clube.backend_application.exceptions.NotFoundException;
import com.campestre.clube.backend_application.entity.Account;
import com.campestre.clube.backend_application.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account login(Account accountRequest) {
        if (!accountRepository.existsByEmailAndPassword(accountRequest.getEmail(), accountRequest.getPassword()))
            throw new BadRequestException("Incorrect email or password");

        return accountRepository.findByEmailAndPassword(accountRequest.getEmail(), accountRequest.getPassword());
    }

    public Account register(Account account) {
        if (accountRepository.existsByEmailOrCpf(account.getEmail(), account.getCpf()))
            throw new ConflictException("User with existing email or CPF");

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

    public Account update(Integer id, Account newAccount){
        Optional<Account> account = accountRepository.findById(id);

        userNotFoundValidation(account, id);
        if (accountRepository.existsByEmailAndIdNot(newAccount.getEmail(), id))
            throw new ConflictException("User with existing email");

        account.get().setId(id);
        account.get().setEmail(newAccount.getEmail());
        account.get().setPassword(newAccount.getPassword());
        account.get().setCpf(newAccount.getCpf());
        account.get().setAccess(newAccount.getAccess());
        return accountRepository.save(account.get());
    }

    public void delete(Integer id){
        if(!accountRepository.existsById(id))
            throw new NotFoundException("User by id [%s] not found".formatted(id));

        accountRepository.deleteById(id);
    }



    private void userNotFoundValidation(Optional<Account> account, Integer id) {
        if (account.isEmpty())
            throw new NotFoundException("User by id [%s] not found".formatted(id));
    }
}
