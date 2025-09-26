package com.campestre.clube.backend_application.core.application.memberdata.command;

import com.campestre.clube.backend_application.core.application.valueobject.Pagination;

public record ListMemberDataByUnitIdAndPaginationCommand(
        Integer unitId,
        Pagination pagination
){}