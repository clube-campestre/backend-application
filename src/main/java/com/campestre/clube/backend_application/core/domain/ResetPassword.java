package com.campestre.clube.backend_application.core.domain;

import java.time.LocalDateTime;

public class ResetPassword {
    private Long id;
    private Account account;
    private String code;
    private LocalDateTime expiration;
    private Boolean used = false;

    private ResetPassword(Long id, Account account, String code, LocalDateTime expiration, Boolean used) {
        this.id = id;
        this.account = account;
        this.code = code;
        this.expiration = expiration;
        this.used = used;
    }

    public static ResetPassword of(Long id, Account account, String code, LocalDateTime expiration, Boolean used) {
        return new ResetPassword(id, account, code, expiration, used);
    }

    public static ResetPassword of(Account account, String code, LocalDateTime expiration, Boolean used) {
        return new ResetPassword(null, account, code, expiration, used);
    }

    public Long getId() {
        return id;
    }

    public Account getAccount() {
        return account;
    }

    public String getCode() {
        return code;
    }

    public LocalDateTime getExpiration() {
        return expiration;
    }

    public Boolean getUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }
}
