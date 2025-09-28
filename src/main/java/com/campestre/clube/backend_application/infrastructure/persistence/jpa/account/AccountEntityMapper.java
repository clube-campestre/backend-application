package com.campestre.clube.backend_application.infrastructure.persistence.jpa.account;

import com.campestre.clube.backend_application.core.domain.Account;

import java.util.List;
import java.util.stream.Collectors;

public class AccountEntityMapper {
    public static AccountEntity toEntity(Account domain) {
        if (domain == null) return null;
        AccountEntity entity = new AccountEntity();
        entity.setId(domain.getId());
        entity.setEmail(domain.getEmail().getValue());
        entity.setPassword(domain.getPassword());
        entity.setName(domain.getName());
        entity.setAccess(domain.getAccess());
        return entity;
    }

    public static Account toDomain(AccountEntity entity) {
        if (entity == null) return null;
        return Account.of(
                entity.getId(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getName(),
                entity.getAccess()
        );
    }

    public static List<Account> toDomain(List<AccountEntity> entities) {
        if (entities == null) return null;
        return entities.stream().map(AccountEntityMapper::toDomain).collect(Collectors.toList());
    }
}
