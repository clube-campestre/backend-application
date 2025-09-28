package com.campestre.clube.backend_application.core.application.account.command;

import com.campestre.clube.backend_application.core.domain.enums.AccessTypeEnum;

public record CreateAccountCommand(
        String email,
        String password,
        String name,
        AccessTypeEnum access
){}