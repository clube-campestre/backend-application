package com.campestre.clube.backend_application.core.application.account.command;

import com.campestre.clube.backend_application.core.domain.enums.AccessTypeEnum;

public record UpdateAccountCommand(
        Long id,
        String email,
        String name,
        AccessTypeEnum access
){}