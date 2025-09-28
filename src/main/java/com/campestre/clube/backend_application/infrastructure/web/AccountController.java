package com.campestre.clube.backend_application.infrastructure.web;

import com.campestre.clube.backend_application.core.application.account.*;
import com.campestre.clube.backend_application.core.application.account.command.DeleteAccountCommand;
import com.campestre.clube.backend_application.core.application.account.command.GetAccountByIdCommand;
import com.campestre.clube.backend_application.core.domain.Account;
import com.campestre.clube.backend_application.infrastructure.web.dtos.account.*;
import com.campestre.clube.backend_application.infrastructure.web.mappers.AccountDtoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@CrossOrigin("*")
@Tag(name = "Account Controller", description = "Account data routes")
public class AccountController {
    private final UpdateAccountUseCase updateAccountUseCase;
    private final DeleteAccountUseCase deleteAccountUseCase;
    private final GetAccountByIdUseCase getAccountByIdUseCase;
    private final ListAccountUseCase listAccountUseCase;
    private final AuthenticateAccountUseCase authenticateAccountUseCase;
    private final CreateAccountUseCase createAccountUseCase;

    public AccountController(
            CreateAccountUseCase createAccountUseCase,
            UpdateAccountUseCase updateAccountUseCase,
            DeleteAccountUseCase deleteAccountUseCase,
            GetAccountByIdUseCase getAccountByIdUseCase,
            ListAccountUseCase listAccountUseCase,
            AuthenticateAccountUseCase authenticateAccountUseCase
    ) {
        this.updateAccountUseCase = updateAccountUseCase;
        this.deleteAccountUseCase = deleteAccountUseCase;
        this.getAccountByIdUseCase = getAccountByIdUseCase;
        this.listAccountUseCase = listAccountUseCase;
        this.authenticateAccountUseCase = authenticateAccountUseCase;
        this.createAccountUseCase = createAccountUseCase;
    }

    @Operation(summary = "Endpoint for account register")
    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody SaveAccountRequestDto dto) {
        createAccountUseCase.execute(AccountDtoMapper.toCommand(dto));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Endpoint for account login")
    @PostMapping("/login")
    public ResponseEntity<TokenAccountResponseDto> login(@RequestBody LoginAccountRequestDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(AccountDtoMapper.toResponse(
                authenticateAccountUseCase.execute(AccountDtoMapper.toCommand(dto))
        ));
    }

    @Operation(summary = "Endpoint for list all accounts")
    @GetMapping
    public ResponseEntity<List<GetAccountResponseDto>> getAll() {
        List<Account> accounts = listAccountUseCase.execute();
        if (accounts.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.status(HttpStatus.OK).body(AccountDtoMapper.toResponse(accounts));
    }

    @Operation(summary = "Endpoint for get account by id")
    @GetMapping("/{id}")
    public ResponseEntity<GetAccountResponseDto> getById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(AccountDtoMapper.toResponse(
                getAccountByIdUseCase.execute(new GetAccountByIdCommand(id))
        ));
    }

    @Operation(summary = "Endpoint for update account by id")
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
            @PathVariable Integer id, @Valid @RequestBody UpdateAccountRequestDto dto
    ) {
        updateAccountUseCase.execute(AccountDtoMapper.toCommand(dto, id));
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Endpoint for remove account by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        deleteAccountUseCase.execute(new DeleteAccountCommand(id));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
