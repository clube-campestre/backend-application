package com.campestre.clube.backend_application.entity;

import com.campestre.clube.backend_application.entity.enums.AccessTypeEnum;
import jakarta.persistence.*;

@Entity(name = "user_account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    @Column(name = "passwd")
    private String password;
    private String cpf;
    private AccessTypeEnum access;

    public Account() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public AccessTypeEnum getAccess() {
        return access;
    }

    public void setAccess(AccessTypeEnum access) {
        this.access = access;
    }
}
