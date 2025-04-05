package com.campestre.clube.backend_application.controller.dtos.responses;

import com.campestre.clube.backend_application.entity.Account;

public class LoginAccountResponseDto {
    private String email;
    private String access;

    public static LoginAccountResponseDto toResponse(Account account) {
        LoginAccountResponseDto response = new LoginAccountResponseDto();
        response.setEmail(account.getEmail());
        response.setAccess(account.getAccess().name());
        return response;
    }

    public LoginAccountResponseDto() {}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }
}
