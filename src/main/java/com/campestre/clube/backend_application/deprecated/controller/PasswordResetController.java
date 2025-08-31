package com.campestre.clube.backend_application.deprecated.controller;

import com.campestre.clube.backend_application.deprecated.service.PasswordResetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class PasswordResetController {

    private final PasswordResetService passwordResetService;

    public PasswordResetController(PasswordResetService passwordResetService) {
        this.passwordResetService = passwordResetService;
    }

    // 1. Solicitar código
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        passwordResetService.generateResetCode(email);
        return ResponseEntity.ok("Código enviado para o e-mail.");
    }

    // 2. Validar código
    @PostMapping("/verify-code")
    public ResponseEntity<String> verifyCode(@RequestParam String email, @RequestParam String code) {
        if (passwordResetService.validateCode(email, code)) {
            return ResponseEntity.ok("Código válido.");
        }
        return ResponseEntity.badRequest().body("Código inválido.");
    }

    // 3. Redefinir senha
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(
            @RequestParam String email,
            @RequestParam String code,
            @RequestParam String newPassword
    ) {
        passwordResetService.resetPassword(email, code, newPassword);
        return ResponseEntity.ok("Senha redefinida com sucesso.");
    }
}
