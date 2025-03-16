package com.campestre.clube.backend_application.model;

public class AccountRequest {
    private String email;
    private String password;
    private String cpf;

    public AccountRequest() {}

    public AccountRequest(String email, String password, String cpf) {
        this.email = email;
        this.password = password;
        this.cpf = cpf;
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
}
