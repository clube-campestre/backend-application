package com.campestre.clube.backend_application.core.application.memberdata.command;

import com.campestre.clube.backend_application.core.domain.enums.*;

public record UpdateMemberUnitAndClassCommand(
        String cpf,
        String unitName,
        UnitRole unitRole,
        ClassCategory classCategory,
        ClassRole classRole
){}