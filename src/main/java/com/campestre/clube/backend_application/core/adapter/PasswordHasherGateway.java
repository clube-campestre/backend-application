package com.campestre.clube.backend_application.core.adapter;

public interface PasswordHasherGateway {
    String hash(String rawPassword);
    boolean matches(String rawPassword, String hashedPassword);
}
