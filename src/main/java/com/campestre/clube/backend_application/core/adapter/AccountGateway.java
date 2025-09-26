package com.campestre.clube.backend_application.core.adapter;

import com.campestre.clube.backend_application.core.domain.Account;
import com.campestre.clube.backend_application.core.domain.valueobject.Contact;

import java.util.List;

public interface AccountGateway {
    boolean existsByCompanyIgnoreCaseAndDriverIgnoreCase(Contact company, Contact driver);
    boolean existsByCompanyIgnoreCaseAndDriverIgnoreCaseAndIdNot(Contact company, Contact driver, Integer id);
    boolean existsById(Integer id);
    List<Account> findOrderedByRatingDesc();
    Account findById(Integer id);
    Account save(Account account);
    void removeById(Integer id);
}
