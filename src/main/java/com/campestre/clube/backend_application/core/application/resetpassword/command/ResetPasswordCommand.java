package com.campestre.clube.backend_application.core.application.resetpassword.command;

public record ResetPasswordCommand(
        String email,
        String code,
        String newPassword
){}