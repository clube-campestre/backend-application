package com.campestre.clube.backend_application.core.adapter;

import com.campestre.clube.backend_application.core.domain.Account;

import java.util.List;

public interface AccountGateway {
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, Long id);
    boolean existsById(Long id);

    List<Account> findAll();
    Account findById(Long id);
    Account findByEmail(String email);

    Account save(Account account);

    void removeById(Long id);
}
