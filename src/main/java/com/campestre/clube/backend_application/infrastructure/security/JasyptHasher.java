package com.campestre.clube.backend_application.infrastructure.security;

import com.campestre.clube.backend_application.core.adapter.HasherGateway;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.jasypt.salt.SaltGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class JasyptHasher implements HasherGateway {
//    private final StandardPBEStringEncryptor encryptor;
    private final SecretKeySpec keySpec;

    public JasyptHasher(@Value("${jasypt.encryptor.password}") String password) {
        String fixedKey = password.length() >= 16 ? password.substring(0, 16)
                : String.format("%-16s", password);
        this.keySpec = new SecretKeySpec(fixedKey.getBytes(StandardCharsets.UTF_8), "AES");
    }

    private String encryptLogic(String value) {
        if (value == null) return null;
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            byte[] encrypted = cipher.doFinal(value.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criptografar", e);
        }
    }

    private String decryptLogic(String value) {
        if (value == null) return null;
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            byte[] decoded = Base64.getDecoder().decode(value);
            return new String(cipher.doFinal(decoded), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao descriptografar", e);
        }
    }

    @Override
    public String encrypt(String value) {
//        return encryptor.encrypt(value);
        return encryptLogic(value);
    }

    @Override
    public String decrypt(String value) {
//        return encryptor.decrypt(value);
        return decryptLogic(value);
    }
}