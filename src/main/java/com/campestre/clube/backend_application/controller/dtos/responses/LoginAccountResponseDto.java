package com.campestre.clube.backend_application.controller.dtos.responses;

public class LoginAccountResponseDto {
    private String email;
    private String access;

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
