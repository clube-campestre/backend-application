package com.campestre.clube.backend_application.core.application.memberdata;

import com.campestre.clube.backend_application.core.adapter.MemberDataGateway;
import com.campestre.clube.backend_application.core.adapter.UnitGateway;
import com.campestre.clube.backend_application.core.application.memberdata.command.ListMemberDataByFilterAndPaginationCommand;
import com.campestre.clube.backend_application.core.application.memberdata.valueobject.Filter;
import com.campestre.clube.backend_application.core.application.valueobject.Pagination;
import com.campestre.clube.backend_application.core.domain.MemberData;
import com.campestre.clube.backend_application.core.domain.Unit;
import com.campestre.clube.backend_application.core.domain.enums.ClassCategory;
import com.campestre.clube.backend_application.core.domain.enums.UnitEnum;
import org.antlr.v4.runtime.misc.Pair;

import java.util.List;

public class ListMemberDataByFilterAndPaginationUseCase {

    private final MemberDataGateway gateway;
    private final UnitGateway unitGateway;

    public ListMemberDataByFilterAndPaginationUseCase(MemberDataGateway gateway, UnitGateway unitGateway) {
        this.gateway = gateway;
        this.unitGateway = unitGateway;
    }

    public Pair<List<MemberData>, Pagination> execute(ListMemberDataByFilterAndPaginationCommand command) {
        UnitEnum unitEnum = command.unitName() != null ? UnitEnum.fromString(command.unitName()) : null;
        Unit unitEntity = null;

        if (command.unitName() != null && unitGateway.existsBySurnameIgnoreCase(unitEnum.name()))
            unitEntity = unitGateway.findBySurnameIgnoreCase(unitEnum.name());

        ClassCategory classCategoryEnum =
                command.className() != null ? ClassCategory.fromString(command.className()) : null;

        List<MemberData> result = gateway.findByFilterAndPagination(
                new Filter(unitEntity, classCategoryEnum, command.memberName()), command.pagination()
        );
        return new Pair<>(result, command.pagination());
    }
}
