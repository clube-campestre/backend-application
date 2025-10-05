package com.campestre.clube.backend_application.deprecated.service;

import com.campestre.clube.backend_application.deprecated.controller.dtos.EmailMessageDto;
import com.campestre.clube.backend_application.deprecated.entity.Account;
import com.campestre.clube.backend_application.deprecated.entity.PasswordResetCode;
import com.campestre.clube.backend_application.deprecated.repository.AccountRepository;
import com.campestre.clube.backend_application.deprecated.repository.PasswordResetCodeRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class PasswordResetService {

    private final AccountRepository accountRepository;
    private final PasswordResetCodeRepository codeRepository;
    private final PasswordEncoder passwordEncoder;
    private final RabbitMQService rabbitMQService;
    private final String queueName;
//    private final EmailService emailService; // você precisa criar um serviço simples para envio de e-mail

    public PasswordResetService(AccountRepository accountRepository, PasswordResetCodeRepository codeRepository, PasswordEncoder passwordEncoder, RabbitMQService rabbitMQService, @Value("${rabbitmq.queuename}") String queueName) {
        this.accountRepository = accountRepository;
        this.codeRepository = codeRepository;
        this.passwordEncoder = passwordEncoder;
        this.rabbitMQService = rabbitMQService;
        this.queueName = queueName;
//        this.emailService = emailService;
    }

    // 1. Solicitar código
    public void generateResetCode(String email) {

        if(!accountRepository.existsByEmail(email))
            throw new RuntimeException("E-mail não cadastrado");

        Account account = accountRepository.findByEmail(email);

        String code = String.format("%06d", new Random().nextInt(999999)); // 6 dígitos
        PasswordResetCode resetCode = new PasswordResetCode();
        resetCode.setUser(account);
        resetCode.setCode(code);
        resetCode.setExpiration(LocalDateTime.now().plusMinutes(10));
        resetCode.setUsed(false);
        codeRepository.save(resetCode);
        rabbitMQService.publishEmail(email, code, queueName);

//        emailService.sendEmail(email, "Código de recuperação de senha",
//                "Seu código é: " + code + "\nEle expira em 10 minutos.");
    }

    // 2. Validar código
    public boolean validateCode(String email, String code) {
        PasswordResetCode resetCode = codeRepository.findByUserEmailAndCodeAndUsedFalse(email, code)
                .orElseThrow(() -> new RuntimeException("Código inválido ou já usado"));

        if (resetCode.getExpiration().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Código expirado");
        }

        return true;
    }

    // 3. Redefinir senha
    public void resetPassword(String email, String code, String newPassword) {
        PasswordResetCode resetCode = codeRepository.findByUserEmailAndCodeAndUsedFalse(email, code)
                .orElseThrow(() -> new RuntimeException("Código inválido ou já usado"));

        if (resetCode.getExpiration().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Código expirado");
        }

        Account account = resetCode.getUser();
        account.setPassword(passwordEncoder.encode(newPassword));
        accountRepository.save(account);

        resetCode.setUsed(true);
        codeRepository.save(resetCode);
    }
}
