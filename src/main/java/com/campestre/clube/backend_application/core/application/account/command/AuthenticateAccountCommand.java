package com.campestre.clube.backend_application.core.application.account.command;

public record AuthenticateAccountCommand(
        String email,
        String password
){}