package com.campestre.clube.backend_application.controller.mapper;

import com.campestre.clube.backend_application.controller.dtos.requests.LoginAccountRequestDto;
import com.campestre.clube.backend_application.controller.dtos.requests.SaveAccountRequestDto;
import com.campestre.clube.backend_application.controller.dtos.responses.GetAccountResponseDto;
import com.campestre.clube.backend_application.controller.dtos.responses.LoginAccountResponseDto;
import com.campestre.clube.backend_application.controller.dtos.responses.SaveAccountResponseDto;
import com.campestre.clube.backend_application.entity.Account;
import com.campestre.clube.backend_application.entity.enums.AccessTypeEnum;

public abstract class AccountMapper {
    public static Account toEntity(LoginAccountRequestDto dto) {
        Account account = new Account();
        account.setEmail(dto.getEmail());
        account.setPassword(dto.getPassword());
        return account;
    }

    public static Account toEntity(SaveAccountRequestDto dto) {
        Account account = new Account();
        account.setEmail(dto.getEmail());
        account.setPassword(dto.getPassword());
        account.setName(dto.getName());
        account.setAccess(AccessTypeEnum.fromString(dto.getAccess()));
        return account;
    }

    public static GetAccountResponseDto toGetResponse(Account account) {
        GetAccountResponseDto response = new GetAccountResponseDto();
        response.setId(account.getId());
        response.setEmail(account.getEmail());
        response.setPassword(account.getPassword());
        response.setName(account.getName());
        response.setAccess(account.getAccess().name());
        return response;
    }

    public static LoginAccountResponseDto toLoginResponse(Account account) {
        LoginAccountResponseDto response = new LoginAccountResponseDto();
        response.setEmail(account.getEmail());
        response.setAccess(account.getAccess().name());
        return response;
    }

    public static SaveAccountResponseDto toSaveResponse(Account account) {
        SaveAccountResponseDto response = new SaveAccountResponseDto();
        response.setId(account.getId());
        response.setEmail(account.getEmail());
        response.setPassword(account.getPassword());
        response.setName(account.getName());
        response.setAccess(account.getAccess().name());
        return response;
    }
}
