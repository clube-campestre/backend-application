package com.campestre.clube.backend_application.core.adapter;

import com.campestre.clube.backend_application.core.domain.Account;

public interface TokenGeneratorGateway {
    String generate(Account account);
    String getUsernameFromToken(String token);
    boolean validateToken(String token, String username);
}
