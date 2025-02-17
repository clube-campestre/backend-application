package com.campestre.clube.backend_application.repository;

import com.campestre.clube.backend_application.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    Account findByEmailAndPassword(String email, String password);
}
