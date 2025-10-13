package com.campestre.clube.backend_application.infrastructure.persistence.jpa.resetpassword;

import com.campestre.clube.backend_application.core.adapter.ResetPasswordGateway;
import com.campestre.clube.backend_application.core.domain.ResetPassword;
import org.springframework.stereotype.Repository;

@Repository
public class ResetPasswordJpaAdapter implements ResetPasswordGateway {

    private final ResetPasswordJpaRepository repository;

    public ResetPasswordJpaAdapter(ResetPasswordJpaRepository repository) {
        this.repository = repository;
    }


    @Override
    public boolean existsByAccountEmailAndCodeAndNotUsed(String email, String code) {
        return repository.existsByAccountEmailAndCodeAndUsedFalse(email, code);
    }

    @Override
    public ResetPassword findByAccountEmailAndCodeAndNotUsed(String email, String code) {
        return ResetPasswordEntityMapper.toDomain(repository.findByAccountEmailAndCodeAndUsedFalse(email, code));
    }

    @Override
    public ResetPassword save(ResetPassword resetPassword) {
        return ResetPasswordEntityMapper.toDomain(repository.save(ResetPasswordEntityMapper.toEntity(resetPassword)));
    }
}
