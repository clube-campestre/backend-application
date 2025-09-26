package com.campestre.clube.backend_application.core.application.memberdata.command;

import com.campestre.clube.backend_application.core.application.valueobject.Pagination;

public record ListMemberDataByFilterAndPaginationCommand(
        String unitName,
        String className,
        String memberName,
        Pagination pagination
){}