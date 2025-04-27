package com.campestre.clube.backend_application.controller.mapper;

import com.campestre.clube.backend_application.controller.dtos.requests.UpdateAccountRequestDto;
import com.campestre.clube.backend_application.controller.dtos.responses.TokenAccountResponseDto;
import com.campestre.clube.backend_application.controller.dtos.requests.LoginAccountRequestDto;
import com.campestre.clube.backend_application.controller.dtos.requests.SaveAccountRequestDto;
import com.campestre.clube.backend_application.controller.dtos.responses.GetAccountResponseDto;
import com.campestre.clube.backend_application.entity.Account;
import com.campestre.clube.backend_application.entity.enums.AccessTypeEnum;

public abstract class AccountMapper {
    public static Account of(SaveAccountRequestDto dto){
        Account account = new Account();
        account.setEmail(dto.getEmail());
        account.setPassword(dto.getPassword());
        account.setName(dto.getName());
        account.setAccess(AccessTypeEnum.fromString(dto.getAccess()));
        return account;
    }

    public static Account of(UpdateAccountRequestDto dto){
        Account account = new Account();
        account.setEmail(dto.getEmail());
        account.setName(dto.getName());
        account.setAccess(AccessTypeEnum.fromString(dto.getAccess()));
        return account;
    }

    public static Account of(LoginAccountRequestDto dto){
        Account account = new Account();
        account.setEmail(dto.getEmail());
        account.setPassword(dto.getPassword());
        return account;
    }

    public static TokenAccountResponseDto of(Account account, String token){
        TokenAccountResponseDto dto = new TokenAccountResponseDto();
        dto.setUserId(account.getId());
        dto.setEmail(account.getEmail());
        dto.setName(account.getName());
        dto.setAccess(account.getAccess().name());
        dto.setToken(token);
        return dto;
    }

    public static GetAccountResponseDto of(Account account){
        GetAccountResponseDto dto = new GetAccountResponseDto();
        dto.setId(account.getId());
        dto.setEmail(account.getEmail());
        dto.setName(account.getName());
        dto.setAccess(account.getAccess().name());
        return dto;
    }
}
