package com.campestre.clube.backend_application.infrastructure.persistence.jpa.resetpassword;

import com.campestre.clube.backend_application.core.domain.ResetPassword;
import com.campestre.clube.backend_application.infrastructure.persistence.jpa.account.AccountEntityMapper;

public class ResetPasswordEntityMapper {
    public static ResetPasswordEntity toEntity(ResetPassword domain) {
        if (domain == null) return null;
        ResetPasswordEntity entity = new ResetPasswordEntity();
        entity.setId(domain.getId());
        entity.setAccount(AccountEntityMapper.toEntity(domain.getAccount()));
        entity.setCode(domain.getCode());
        entity.setExpiration(domain.getExpiration());
        entity.setUsed(domain.getUsed());
        return entity;
    }

    public static ResetPassword toDomain(ResetPasswordEntity entity) {
        if (entity == null) return null;
        return ResetPassword.of(
                entity.getId(),
                AccountEntityMapper.toDomain(entity.getAccount()),
                entity.getCode(),
                entity.getExpiration(),
                entity.isUsed()
        );
    }
}
