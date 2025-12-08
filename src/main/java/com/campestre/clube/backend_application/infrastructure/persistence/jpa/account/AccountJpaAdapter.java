package com.campestre.clube.backend_application.infrastructure.persistence.jpa.account;

import com.campestre.clube.backend_application.core.adapter.AccountGateway;
import com.campestre.clube.backend_application.core.domain.Account;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountJpaAdapter implements AccountGateway {

    private final AccountJpaRepository repository;

    public AccountJpaAdapter(AccountJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public boolean existsByEmailAndIdNot(String email, Long id) {
        return repository.existsByEmailAndIdNot(email, id);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    @Cacheable(cacheNames = "account.list")
    public List<Account> findAll() {
        return AccountEntityMapper.toDomain(repository.findAll());
    }

    @Override
    @Cacheable(cacheNames = "account.byId", key = "#id")
    public Account findById(Long id) {
        return AccountEntityMapper.toDomain(repository.findById(id).get());
    }

    @Override
    @Cacheable(cacheNames = "account.byEmail", key = "#email")
    public Account findByEmail(String email) {
        return AccountEntityMapper.toDomain(repository.findByEmail(email));
    }

    @Override
    @CacheEvict(cacheNames = {"account.byId", "account.list", "account.email"}, allEntries = true)
    public Account save(Account domain) {
        return AccountEntityMapper.toDomain(repository.save(AccountEntityMapper.toEntity(domain)));
    }

    @Override
    @CacheEvict(cacheNames = {"account.byId", "account.list", "account.email"}, allEntries = true)
    public void removeById(Long id) {
        repository.deleteById(id);
    }
}
