package com.campestre.clube.backend_application.controller.dtos.requests;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SaveAccountRequestDto {
    @NotNull
    @Email
    private String email;

    @Column(name = "passwd")
    @Size(min = 10, max = 20)
    @NotNull
    private String password;

    @NotNull
    private String name;

    @NotNull
    private String access;

    public SaveAccountRequestDto() {}

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
