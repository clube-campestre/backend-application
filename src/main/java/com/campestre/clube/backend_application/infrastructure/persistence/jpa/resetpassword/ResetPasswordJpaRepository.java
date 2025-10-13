package com.campestre.clube.backend_application.infrastructure.persistence.jpa.resetpassword;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ResetPasswordJpaRepository extends JpaRepository<ResetPasswordEntity, Integer> {
    boolean existsByAccountEmailAndCodeAndUsedFalse(String email, String code);

    ResetPasswordEntity findByAccountEmailAndCodeAndUsedFalse(String email, String code);
}
