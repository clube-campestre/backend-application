package com.campestre.clube.backend_application.core.application.memberdata.command;

import com.campestre.clube.backend_application.core.application.valueobject.Pagination;
import com.campestre.clube.backend_application.core.domain.enums.ClassCategory;

public record ListMemberDataByClassCategoryAndPaginationCommand(
        ClassCategory classCategory,
        Pagination pagination
){}