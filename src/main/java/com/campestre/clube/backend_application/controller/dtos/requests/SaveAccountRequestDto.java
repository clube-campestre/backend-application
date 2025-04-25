package com.campestre.clube.backend_application.controller.dtos.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SaveAccountRequestDto {
    @NotNull
    @Email
    @Schema(description = "Account e-mail", example = "test@email.com")
    private String email;

    @Column(name = "passwd")
    @Size(min = 10, max = 20)
    @NotNull
    @Schema(description = "Account password", example = "ABCDE12345")
    private String password;

    @NotEmpty
    @Schema(description = "Account name", example = "Test")
    private String name;

    @NotNull
    @Schema(description = "Account access", example = "DIRETOR", allowableValues = {"DIRETOR", "EXECUTIVO", "TESOURARIA", "SUPERVISOR"})
    private String access;

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
