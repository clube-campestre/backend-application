package com.campestre.clube.backend_application.controller.mapper;

import com.campestre.clube.backend_application.controller.dtos.TokenAccountRequestDto;
import com.campestre.clube.backend_application.controller.dtos.requests.LoginAccountRequestDto;
import com.campestre.clube.backend_application.controller.dtos.requests.SaveAccountRequestDto;
import com.campestre.clube.backend_application.controller.dtos.requests.StatementRequestDto;
import com.campestre.clube.backend_application.controller.dtos.responses.GetAccountResponseDto;
import com.campestre.clube.backend_application.controller.dtos.responses.StatementResponseDto;
import com.campestre.clube.backend_application.entity.Account;
import com.campestre.clube.backend_application.entity.Statement;

public abstract class AccountMapper {
    public static Account of(SaveAccountRequestDto dto){
        Account account = new Account();
        account.setEmail(dto.getEmail());
        account.setPassword(dto.getPassword());
        return account;
    }

    public static Account of(LoginAccountRequestDto dto){
        Account account = new Account();
        account.setEmail(dto.getEmail());
        account.setPassword(dto.getPassword());
        return account;
    }

    public static TokenAccountRequestDto of(Account account, String token){
        TokenAccountRequestDto dto = new TokenAccountRequestDto();
        dto.setUserId(account.getId());
        dto.setEmail(dto.getEmail());
        dto.setToken(token);
        return dto;
    }

    public static GetAccountResponseDto of(Account account){
        GetAccountResponseDto dto = new GetAccountResponseDto();
        dto.setId(account.getId());
        dto.setEmail(dto.getEmail());
        dto.setCpf(dto.getCpf());
        dto.setAccess(dto.getAccess());
        return dto;
    }
}
