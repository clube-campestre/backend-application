package com.campestre.clube.backend_application.controller;

import com.campestre.clube.backend_application.model.Account;
import com.campestre.clube.backend_application.model.AccountRequest;
import com.campestre.clube.backend_application.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody AccountRequest account){
        return accountService.login(account.getEmail(), account.getPassword()) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/register")
    public ResponseEntity<Account> register(@RequestBody Account accountReceive){
        return accountService.save(accountReceive) == null ? ResponseEntity.status(HttpStatus.BAD_REQUEST).build() :
                ResponseEntity.status(HttpStatus.CREATED).body(accountService.save(accountReceive));
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getById(@PathVariable String id){
        Account account = accountService.getById(id);
        return account == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).build() :
                ResponseEntity.status(HttpStatus.OK).body(account);
    }
}
