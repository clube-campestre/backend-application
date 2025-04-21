package com.campestre.clube.backend_application.controller.dtos.requests;

import com.campestre.clube.backend_application.entity.Account;
import com.campestre.clube.backend_application.entity.enums.AccessTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

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

    @CPF
    @NotNull
    @Schema(description = "Account CPF", example = "12312312312")
    private String cpf;

    @NotNull
    @Schema(description = "Account access", allowableValues = {"DIRETOR", "EXECUTIVO", "TESOURARIA", "SUPERVISOR"})
    private String access;

    public static Account toEntity(SaveAccountRequestDto dto) {
        Account account = new Account();
        account.setEmail(dto.getEmail());
        account.setPassword(dto.getPassword());
        account.setCpf(dto.getCpf());
        account.setAccess(AccessTypeEnum.fromString(dto.getAccess()));
        return account;
    }

    public SaveAccountRequestDto() {
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
