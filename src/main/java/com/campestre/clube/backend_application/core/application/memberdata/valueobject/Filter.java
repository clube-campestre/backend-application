package com.campestre.clube.backend_application.core.application.memberdata.valueobject;

import com.campestre.clube.backend_application.core.domain.Unit;
import com.campestre.clube.backend_application.core.domain.enums.ClassCategory;

public record Filter(
        Unit unit,
        ClassCategory classCategory,
        String username
) {}
