package com.campestre.clube.backend_application.controller;

import com.campestre.clube.backend_application.controller.dtos.requests.LoginAccountRequestDto;
import com.campestre.clube.backend_application.controller.dtos.requests.SaveAccountRequestDto;
import com.campestre.clube.backend_application.controller.dtos.responses.GetAccountResponseDto;
import com.campestre.clube.backend_application.controller.dtos.responses.LoginAccountResponseDto;
import com.campestre.clube.backend_application.controller.dtos.responses.SaveAccountResponseDto;
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

    @Operation(summary = "Endpoint for member login")
    @PostMapping("/login")
    public ResponseEntity<LoginAccountResponseDto> login(@RequestBody LoginAccountRequestDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(AccountMapper.toLoginResponse(
                accountService.login(AccountMapper.toEntity(dto))
        ));
    }

    @Operation(summary = "Endpoint for member register")
    @PostMapping("/register")
    public ResponseEntity<SaveAccountResponseDto> register(@Valid @RequestBody SaveAccountRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(AccountMapper.toSaveResponse(
                accountService.register(AccountMapper.toEntity(dto))
        ));
    }

    @Operation(summary = "Endpoint for list all members")
    @GetMapping
    public ResponseEntity<List<GetAccountResponseDto>> getAll() {
        List<Account> accounts = accountService.getAll();
        if (accounts.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.status(HttpStatus.OK).body(accounts.stream().map(AccountMapper::toGetResponse)
                .toList());
    }

    @Operation(summary = "Endpoint for get member by id")
    @GetMapping("/{id}")
    public ResponseEntity<GetAccountResponseDto> getById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(AccountMapper.toGetResponse(accountService.getById(id)));
    }

    @Operation(summary = "Endpoint for update member by id")
    @PutMapping("/{id}")
    public ResponseEntity<SaveAccountResponseDto> update(
            @PathVariable Integer id, @Valid @RequestBody SaveAccountRequestDto account
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(AccountMapper.toSaveResponse(
                accountService.update(id, AccountMapper.toEntity(account))
        ));
    }

    @Operation(summary = "Endpoint for remove member by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        accountService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
