package com.campestre.clube.backend_application.core.application.memberdata;

import com.campestre.clube.backend_application.core.adapter.MemberDataGateway;
import com.campestre.clube.backend_application.core.application.memberdata.command.ListMemberDataByClassCategoryAndPaginationCommand;
import com.campestre.clube.backend_application.core.domain.MemberData;
import com.campestre.clube.backend_application.core.domain.MemberDataForClass;
import com.campestre.clube.backend_application.core.domain.enums.ClassRole;

import java.util.List;

import static com.campestre.clube.backend_application.core.application.extensions.ExceptionExtensions.*;

public class ListMemberDataByClassCategoryAndPaginationUseCase {

    private final MemberDataGateway gateway;

    public ListMemberDataByClassCategoryAndPaginationUseCase(MemberDataGateway gateway) {
        this.gateway = gateway;
    }

    public MemberDataForClass execute(ListMemberDataByClassCategoryAndPaginationCommand command) {
        List<MemberData> instructors =
                gateway.findByClassCategoryAndClassRole(command.classCategory(), ClassRole.INSTRUTOR);

        if (instructors.isEmpty()) throw BAD_REQUEST_CLASS_MUST_HAVE_INSTRUCTOR;
        if (instructors.size() > 1) throw BAD_REQUEST_CLASS_MUST_HAVE_ONLY_INSTRUCTOR;

        List<MemberData> result = gateway.findByClassAndPagination(command.classCategory(), command.pagination());

        return MemberDataForClass.of(
                instructors.getFirst().getUsername(),
                command.pagination().getPageNumber(),
                command.pagination().getPageSize(),
                command.pagination().getTotalItems(),
                command.pagination().getTotalPages(),
                result
        );
    }
}
