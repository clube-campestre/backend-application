package com.campestre.clube.backend_application.infrastructure.web.dtos.resetpassword;

import com.campestre.clube.backend_application.core.domain.Account;

import java.time.LocalDateTime;

public class ResetPasswordRequestDto {
    private Account account;
    private String code;
    private LocalDateTime expiration;
    private Boolean used;

    public ResetPasswordRequestDto(Account account, String code, LocalDateTime expiration, Boolean used) {
        this.account = account;
        this.code = code;
        this.expiration = expiration;
        this.used = used;
    }

    public ResetPasswordRequestDto() {
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpiration() {
        return expiration;
    }

    public void setExpiration(LocalDateTime expiration) {
        this.expiration = expiration;
    }

    public Boolean getUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }
}
