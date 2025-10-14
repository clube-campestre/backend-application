package com.campestre.clube.backend_application.infrastructure.web.mappers;

import com.campestre.clube.backend_application.core.application.account.command.AuthenticateAccountCommand;
import com.campestre.clube.backend_application.core.application.account.command.CreateAccountCommand;
import com.campestre.clube.backend_application.core.application.account.command.UpdateAccountCommand;
import com.campestre.clube.backend_application.core.domain.Account;
import com.campestre.clube.backend_application.core.domain.LoginAccount;
import com.campestre.clube.backend_application.core.domain.enums.AccessTypeEnum;
import com.campestre.clube.backend_application.infrastructure.persistence.jpa.account.AccountEntityMapper;
import com.campestre.clube.backend_application.infrastructure.web.dtos.account.*;

import java.util.List;
import java.util.stream.Collectors;

public class AccountDtoMapper {

    public static UpdateAccountCommand toCommand(UpdateAccountRequestDto dto, Long id) {
        if (dto == null) return null;
        return new UpdateAccountCommand(
                id,
                dto.getEmail(),
                dto.getName(),
                AccessTypeEnum.fromString(dto.getAccess())
        );
    }

    public static AuthenticateAccountCommand toCommand(LoginAccountRequestDto dto) {
        if (dto == null) return null;
        return new AuthenticateAccountCommand(
                dto.getEmail(),
                dto.getPassword()
        );
    }

    public static CreateAccountCommand toCommand(SaveAccountRequestDto dto) {
        if (dto == null) return null;
        return new CreateAccountCommand(
                dto.getEmail(),
                dto.getPassword(),
                dto.getName(),
                AccessTypeEnum.fromString(dto.getAccess())
        );
    }

    public static GetAccountResponseDto toResponse(Account domain) {
        if (domain == null) return null;
        return new GetAccountResponseDto(
                domain.getId(),
                domain.getEmail().getValue(),
                domain.getName(),
                domain.getAccess().getFormattedValue()
        );
    }

    public static TokenAccountResponseDto toResponse(LoginAccount domain) {
        if (domain == null) return null;
        return new TokenAccountResponseDto(
                domain.getUserId(),
                domain.getEmail(),
                domain.getToken(),
                domain.getName(),
                domain.getAccess()
        );
    }

    public static List<GetAccountResponseDto> toResponse(List<Account> domains) {
        if (domains == null) return null;
        return domains.stream().map(AccountDtoMapper::toResponse).collect(Collectors.toList());
    }
}