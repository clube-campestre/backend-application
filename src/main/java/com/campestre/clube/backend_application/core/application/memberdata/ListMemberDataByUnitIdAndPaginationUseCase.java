package com.campestre.clube.backend_application.core.application.memberdata;

import com.campestre.clube.backend_application.core.adapter.MemberDataGateway;
import com.campestre.clube.backend_application.core.adapter.UnitGateway;
import com.campestre.clube.backend_application.core.application.memberdata.command.ListMemberDataByUnitIdAndPaginationCommand;
import com.campestre.clube.backend_application.core.domain.MemberData;
import com.campestre.clube.backend_application.core.domain.MemberDataForUnit;
import com.campestre.clube.backend_application.core.domain.Unit;
import com.campestre.clube.backend_application.core.domain.enums.UnitRole;

import java.util.List;

import static com.campestre.clube.backend_application.core.application.extensions.ExceptionExtensions.*;

public class ListMemberDataByUnitIdAndPaginationUseCase {

    private final MemberDataGateway gateway;
    private final UnitGateway unitGateway;

    public ListMemberDataByUnitIdAndPaginationUseCase(MemberDataGateway gateway, UnitGateway unitGateway) {
        this.gateway = gateway;
        this.unitGateway = unitGateway;
    }

    public MemberDataForUnit execute(ListMemberDataByUnitIdAndPaginationCommand command) {
        if (!unitGateway.existsById(command.unitId())) throw NOT_FOUND_UNIT;

        Unit unit = unitGateway.findById(command.unitId());
        List<MemberData> counselors = gateway.findByUnitIdAndUnitRole(command.unitId(), UnitRole.CONSELHEIRO);

        if (counselors.isEmpty()) throw BAD_REQUEST_UNIT_MUST_HAVE_COUNSELOR;
        if (counselors.size() > 1) throw BAD_REQUEST_UNIT_MUST_HAVE_ONLY_COUNSELOR;

        List<MemberData> result = gateway.findByUnitIdAndPagination(command.unitId(), command.pagination());

        return MemberDataForUnit.of(
                unit.getScore(),
                counselors.getFirst().getUsername(),
                command.pagination().getPageNumber(),
                command.pagination().getPageSize(),
                command.pagination().getTotalItems(),
                command.pagination().getTotalPages(),
                result
        );
    }
}
