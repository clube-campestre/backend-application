package com.campestre.clube.backend_application.controller;

import com.campestre.clube.backend_application.entity.Account;
import com.campestre.clube.backend_application.entity.AccountRequest;
import com.campestre.clube.backend_application.service.AccountService;
import jakarta.validation.Valid;
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
    public ResponseEntity<Account> login(@RequestBody AccountRequest account) {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.login(account));
    }

    @PostMapping("/register")
    public ResponseEntity<Account> register(@Valid @RequestBody Account accountReceive) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.register(accountReceive));
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAll() {
        List<Account> accounts = accountService.getAll();
        if (accounts.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.status(HttpStatus.OK).body(accounts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> update(@PathVariable Integer id, @Valid @RequestBody Account account) {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.update(id, account));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        accountService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
