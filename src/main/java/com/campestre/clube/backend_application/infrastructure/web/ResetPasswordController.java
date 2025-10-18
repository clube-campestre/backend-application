package com.campestre.clube.backend_application.infrastructure.web;

import com.campestre.clube.backend_application.core.application.resetpassword.GenerateResetPasswordCodeUseCase;
import com.campestre.clube.backend_application.core.application.resetpassword.ResetPasswordUseCase;
import com.campestre.clube.backend_application.core.application.resetpassword.ValidateResetPasswordCodeUseCase;
import com.campestre.clube.backend_application.core.application.resetpassword.command.GenerateResetPasswordCodeCommand;
import com.campestre.clube.backend_application.core.application.resetpassword.command.ResetPasswordCommand;
import com.campestre.clube.backend_application.core.application.resetpassword.command.ValidateResetPasswordCodeCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/reset-password")
@Tag(name = "Reset Password Controller", description = "Reset password routes")
public class ResetPasswordController {
    private final GenerateResetPasswordCodeUseCase generateResetPasswordCodeUseCase;
    private final ResetPasswordUseCase resetPasswordUseCase;
    private final ValidateResetPasswordCodeUseCase validateResetPasswordCodeUseCase;

    public ResetPasswordController(
            GenerateResetPasswordCodeUseCase generateResetPasswordCodeUseCase,
            ResetPasswordUseCase resetPasswordUseCase,
            ValidateResetPasswordCodeUseCase validateResetPasswordCodeUseCase
    ) {
        this.generateResetPasswordCodeUseCase = generateResetPasswordCodeUseCase;
        this.resetPasswordUseCase = resetPasswordUseCase;
        this.validateResetPasswordCodeUseCase = validateResetPasswordCodeUseCase;
    }

    @PostMapping("/reset")
    @Operation(summary = "Endpoint for generate reset password code")
    @PreAuthorize("hasAnyRole('DIRETOR', 'EXECUTIVO', 'TESOURARIA', 'SUPERVISOR')")
    public ResponseEntity<Void> resetPassword(@RequestParam String email) {
        generateResetPasswordCodeUseCase.execute(new GenerateResetPasswordCodeCommand(email));
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/verify-code")
    @Operation(summary = "Endpoint for verify reset password code")
    @PreAuthorize("hasAnyRole('DIRETOR', 'EXECUTIVO', 'TESOURARIA', 'SUPERVISOR')")
    public ResponseEntity<Boolean> verifyCode(@RequestParam String email, @RequestParam String code) {
        if (validateResetPasswordCodeUseCase.execute(new ValidateResetPasswordCodeCommand(email, code)))
            return ResponseEntity.ok(true);
        return ResponseEntity.badRequest().body(false);
    }

    @PostMapping("/update-password")
    @Operation(summary = "Endpoint for update password")
    @PreAuthorize("hasAnyRole('DIRETOR', 'EXECUTIVO', 'TESOURARIA', 'SUPERVISOR')")
    public ResponseEntity<Void> updatePassword(
            @RequestParam String email, @RequestParam String code, @RequestParam String newPassword
    ) {
        resetPasswordUseCase.execute(new ResetPasswordCommand(email, code, newPassword));
        return ResponseEntity.noContent().build();
    }
}
