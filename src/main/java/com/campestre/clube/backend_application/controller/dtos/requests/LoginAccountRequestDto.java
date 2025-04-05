package com.campestre.clube.backend_application.controller.dtos.requests;

import com.campestre.clube.backend_application.entity.Account;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginAccountRequestDto {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;

    public static Account toEntity(LoginAccountRequestDto dto) {
        Account account = new Account();
        account.setEmail(dto.getEmail());
        account.setPassword(dto.getPassword());
        return account;
    }

    public LoginAccountRequestDto() {}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
