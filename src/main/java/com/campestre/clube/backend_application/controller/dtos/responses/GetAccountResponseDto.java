package com.campestre.clube.backend_application.controller.dtos.responses;

import com.campestre.clube.backend_application.entity.Account;

public class GetAccountResponseDto {
    private Integer id;
    private String email;
    private String password;
    private String cpf;
    private String access;

    public static GetAccountResponseDto toResponse(Account account) {
        GetAccountResponseDto response = new GetAccountResponseDto();
        response.setId(account.getId());
        response.setEmail(account.getEmail());
        response.setPassword(account.getPassword());
        response.setCpf(account.getCpf());
        response.setAccess(account.getAccess().name());
        return response;
    }

    public GetAccountResponseDto() {}

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

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }
}
