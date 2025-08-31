package com.campestre.clube.backend_application.deprecated.repository;

import com.campestre.clube.backend_application.deprecated.entity.PasswordResetCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetCodeRepository extends JpaRepository<PasswordResetCode, Long> {
    Optional<PasswordResetCode> findByUserEmailAndCodeAndUsedFalse(String email, String code);
}
