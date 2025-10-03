package com.campestre.clube.backend_application.infrastructure.security;

import com.campestre.clube.backend_application.core.adapter.HasherGateway;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JasyptHasher implements HasherGateway {
    private final BasicTextEncryptor encryptor;

    public JasyptHasher(@Value("${jasypt.encryptor.password}") String password) {
        this.encryptor = new BasicTextEncryptor();
        this.encryptor.setPassword(password);
    }

    @Override
    public String crypt(String value) {
        return encryptor.encrypt(value);
    }

    @Override
    public String decrypt(String value) {
        return encryptor.decrypt(value);
    }
}