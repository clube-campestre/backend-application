package com.campestre.clube.backend_application.core.adapter;

import com.campestre.clube.backend_application.core.domain.Account;
import com.campestre.clube.backend_application.core.domain.valueobject.Email;

import java.util.List;

public interface AccountGateway {
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, Integer id);
    boolean existsById(Integer id);

    List<Account> findAll();
    Account findById(Integer id);
    Account findByEmail(String email);

    Account save(Account account);

    void removeById(Integer id);
}
