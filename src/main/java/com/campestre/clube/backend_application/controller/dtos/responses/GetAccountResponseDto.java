package com.campestre.clube.backend_application.controller.dtos.responses;

import com.campestre.clube.backend_application.entity.Account;
import io.swagger.v3.oas.annotations.media.Schema;

public class GetAccountResponseDto {
    @Schema(description = "Account ID", example = "1")
    private Integer id;
    @Schema(description = "Account e-mail", example = "test@email.com")
    private String email;
    @Schema(description = "Account name", example = "Test")
    private String name;
    @Schema(description = "Account access", allowableValues = {"DIRETOR", "EXECUTIVO", "TESOURARIA", "SUPERVISOR"})
    private String access;

    public static GetAccountResponseDto toResponse(Account account) {
        GetAccountResponseDto response = new GetAccountResponseDto();
        response.setId(account.getId());
        response.setEmail(account.getEmail());
        response.setName(account.getName());
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
