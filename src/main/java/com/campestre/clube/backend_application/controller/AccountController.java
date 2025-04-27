package com.campestre.clube.backend_application.controller;

import com.campestre.clube.backend_application.controller.dtos.requests.UpdateAccountRequestDto;
import com.campestre.clube.backend_application.controller.dtos.responses.TokenAccountResponseDto;
import com.campestre.clube.backend_application.controller.dtos.requests.LoginAccountRequestDto;
import com.campestre.clube.backend_application.controller.dtos.requests.SaveAccountRequestDto;
import com.campestre.clube.backend_application.controller.dtos.responses.GetAccountResponseDto;
import com.campestre.clube.backend_application.controller.mapper.AccountMapper;
import com.campestre.clube.backend_application.entity.Account;
import com.campestre.clube.backend_application.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@CrossOrigin("*")
@Tag(name = "Account Controller", description = "Member account data routes")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Operation(summary = "Endpoint for member register")
    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody SaveAccountRequestDto dto) {
        this.accountService.register(AccountMapper.of(dto));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Endpoint for member login")
    @PostMapping("/login")
    public ResponseEntity<TokenAccountResponseDto> login(@RequestBody LoginAccountRequestDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(this.accountService.authenticate(AccountMapper.of(dto)));
    }

    @Operation(summary = "Endpoint for list all members")
    @GetMapping
    public ResponseEntity<List<GetAccountResponseDto>> getAll() {
        List<Account> accounts = accountService.getAll();
        if (accounts.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.status(HttpStatus.OK).body(accounts.stream().map(AccountMapper::of)
                .toList());
    }

    @Operation(summary = "Endpoint for get member by id")
    @GetMapping("/{id}")
    public ResponseEntity<GetAccountResponseDto> getById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(AccountMapper.of(accountService.getById(id)));
    }

    @Operation(summary = "Endpoint for update member by id")
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
            @PathVariable Integer id, @Valid @RequestBody UpdateAccountRequestDto account
    ) {
        accountService.update(id, AccountMapper.of(account));
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Endpoint for remove member by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        accountService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
