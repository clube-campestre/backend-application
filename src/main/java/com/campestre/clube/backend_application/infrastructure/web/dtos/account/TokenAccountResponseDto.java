package com.campestre.clube.backend_application.infrastructure.web.dtos.account;

public class TokenAccountResponseDto {
    private Long userId;
    private String email;
    private String token;
    private String name;
    private String access;

    public TokenAccountResponseDto(Long userId, String email, String token, String name, String access) {
        this.userId = userId;
        this.email = email;
        this.token = token;
        this.name = name;
        this.access = access;
    }

    public TokenAccountResponseDto() {}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }
}
