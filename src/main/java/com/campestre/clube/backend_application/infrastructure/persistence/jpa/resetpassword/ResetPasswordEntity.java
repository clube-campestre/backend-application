package com.campestre.clube.backend_application.infrastructure.persistence.jpa.resetpassword;

import com.campestre.clube.backend_application.infrastructure.persistence.jpa.account.AccountEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reset_password")
public class ResetPasswordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "account")
    private AccountEntity account;

    @Column(name = "code", nullable = false, length = 10)
    private String code = "";

    @Column(name = "expiration", nullable = false)
    private LocalDateTime expiration;

    @Column(name = "used", nullable = false)
    private boolean used = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
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

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
