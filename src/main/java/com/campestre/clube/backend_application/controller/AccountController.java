package com.campestre.clube.backend_application.controller;

import com.campestre.clube.backend_application.controller.dtos.requests.LoginAccountRequestDto;
import com.campestre.clube.backend_application.controller.dtos.requests.SaveAccountRequestDto;
import com.campestre.clube.backend_application.controller.dtos.responses.GetAccountResponseDto;
import com.campestre.clube.backend_application.controller.dtos.responses.LoginAccountResponseDto;
import com.campestre.clube.backend_application.controller.dtos.responses.SaveAccountResponseDto;
import com.campestre.clube.backend_application.entity.Account;
import com.campestre.clube.backend_application.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
//@Tag(name = "Account Controller", description = "Member account data routes")
public class AccountController {

    @Autowired
    private AccountService accountService;

    //    @Operation(summary = "Endpoint for member login")
    @PostMapping("/login")
    public ResponseEntity<LoginAccountResponseDto> login(@RequestBody LoginAccountRequestDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(LoginAccountResponseDto.toResponse(
                accountService.login(LoginAccountRequestDto.toEntity(dto))
        ));
    }

    @PostMapping("/register")
    public ResponseEntity<SaveAccountResponseDto> register(@Valid @RequestBody SaveAccountRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(SaveAccountResponseDto.toResponse(
                accountService.register(SaveAccountRequestDto.toEntity(dto))
        ));
    }

    @GetMapping
    public ResponseEntity<List<GetAccountResponseDto>> getAll() {
        List<Account> accounts = accountService.getAll();
        if (accounts.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.status(HttpStatus.OK).body(accounts.stream().map(GetAccountResponseDto::toResponse)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetAccountResponseDto> getById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(GetAccountResponseDto.toResponse(accountService.getById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaveAccountResponseDto> update(
            @PathVariable Integer id, @Valid @RequestBody SaveAccountRequestDto account
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(SaveAccountResponseDto.toResponse(
                accountService.update(id, SaveAccountRequestDto.toEntity(account))
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        accountService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
