package com.campestre.clube.backend_application.controller.dtos.responses;

import com.campestre.clube.backend_application.entity.Account;
import io.swagger.v3.oas.annotations.media.Schema;

public class GetAccountResponseDto {
    @Schema(description = "Account ID", example = "1")
    private Long id;
    @Schema(description = "Account e-mail", example = "test@email.com")
    private String email;
    @Schema(description = "Account CPF", example = "12312312312")
    private String cpf;
    @Schema(description = "Account access", allowableValues = {"DIRETOR", "EXECUTIVO", "TESOURARIA", "SUPERVISOR"})
    private String access;

    public static GetAccountResponseDto toResponse(Account account) {
        GetAccountResponseDto response = new GetAccountResponseDto();
        response.setId(account.getId());
        response.setEmail(account.getEmail());
        response.setCpf(account.getCpf());
        response.setAccess(account.getAccess().name());
        return response;
    }

    public GetAccountResponseDto() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
