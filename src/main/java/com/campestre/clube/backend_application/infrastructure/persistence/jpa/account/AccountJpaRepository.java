package com.campestre.clube.backend_application.infrastructure.persistence.jpa.account;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountJpaRepository extends JpaRepository<AccountEntity, Long> {
    Boolean existsByEmailAndIdNot(String email, Long id);
    Boolean existsByEmail(String email);

    AccountEntity findByEmail(String email);
}
