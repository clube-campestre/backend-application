package com.campestre.clube.backend_application.core.application.resetpassword.command;

public record ValidateResetPasswordCodeCommand(String email, String code){}