package com.campestre.clube.backend_application.core.domain;

import com.campestre.clube.backend_application.core.domain.enums.AccessTypeEnum;
import com.campestre.clube.backend_application.core.domain.valueobject.Email;

public class Account {
    private Integer id;
    private Email email;
    private String password;
    private String name;
    private AccessTypeEnum access;

    private Account(Integer id, Email email, String password, String name, AccessTypeEnum access) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.access = access;
    }

    public static Account of(Integer id, String email, String password, String name, AccessTypeEnum access) {
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

    public Integer getId() {
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
