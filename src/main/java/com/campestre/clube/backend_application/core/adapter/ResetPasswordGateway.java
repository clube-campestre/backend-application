package com.campestre.clube.backend_application.core.adapter;

import com.campestre.clube.backend_application.core.domain.ResetPassword;

public interface ResetPasswordGateway {
    boolean existsByAccountEmailAndCodeAndNotUsed(String email, String code);

    ResetPassword findByAccountEmailAndCodeAndNotUsed(String email, String code);

    ResetPassword save(ResetPassword resetPassword);
}
