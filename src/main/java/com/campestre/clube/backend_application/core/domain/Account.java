package com.campestre.clube.backend_application.core.domain;

import com.campestre.clube.backend_application.core.domain.enums.AccessTypeEnum;
import com.campestre.clube.backend_application.core.domain.valueobject.Email;

public class Account {
    private Long id;
    private Email email;
    private String password;
    private String name;
    private AccessTypeEnum access;

    private Account(Long id, Email email, String password, String name, AccessTypeEnum access) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.access = access;
    }

    public static Account of(Long id, String email, String password, String name, AccessTypeEnum access) {
        return new Account(
                id,
                Email.of(email),
                password,
                name,
                access
        );
    }

    public static Account of(String email, String password, String name, AccessTypeEnum access) {
        return new Account(
                null,
                Email.of(email),
                password,
                name,
                access
        );
    }

    public Long getId() {
        return id;
    }

    public Email getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public AccessTypeEnum getAccess() {
        return access;
    }
}
