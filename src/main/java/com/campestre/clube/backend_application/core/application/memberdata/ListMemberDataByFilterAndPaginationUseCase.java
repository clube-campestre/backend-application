package com.campestre.clube.backend_application.core.application.memberdata;

import com.campestre.clube.backend_application.core.adapter.HasherGateway;
import com.campestre.clube.backend_application.core.adapter.MemberDataGateway;
import com.campestre.clube.backend_application.core.adapter.UnitGateway;
import com.campestre.clube.backend_application.core.application.memberdata.command.ListMemberDataByFilterAndPaginationCommand;
import com.campestre.clube.backend_application.core.application.memberdata.valueobject.Filter;
import com.campestre.clube.backend_application.core.application.valueobject.Pagination;
import com.campestre.clube.backend_application.core.domain.MemberData;
import com.campestre.clube.backend_application.core.domain.Unit;
import com.campestre.clube.backend_application.core.domain.enums.ClassCategory;
import com.campestre.clube.backend_application.core.domain.enums.UnitEnum;
import com.campestre.clube.backend_application.core.domain.valueobject.Cns;
import com.campestre.clube.backend_application.core.domain.valueobject.Cpf;
import org.antlr.v4.runtime.misc.Pair;

import java.util.List;

public class ListMemberDataByFilterAndPaginationUseCase {

    private final MemberDataGateway gateway;
    private final UnitGateway unitGateway;
    private final HasherGateway hasherGateway;

    public ListMemberDataByFilterAndPaginationUseCase(
            MemberDataGateway gateway, UnitGateway unitGateway, HasherGateway hasherGateway
    ) {
        this.gateway = gateway;
        this.unitGateway = unitGateway;
        this.hasherGateway = hasherGateway;
    }

    public Pair<List<MemberData>, Pagination> execute(ListMemberDataByFilterAndPaginationCommand command) {
        UnitEnum unitEnum = command.unitName() != null && !command.unitName().isBlank()
                ? UnitEnum.fromString(command.unitName()) : null;
        Unit unitEntity = null;

        if (command.unitName() != null && !command.unitName().isBlank() && unitGateway.existsBySurnameIgnoreCase(unitEnum.name()))
            unitEntity = unitGateway.findBySurnameIgnoreCase(unitEnum.name());

        ClassCategory classCategoryEnum = command.className() != null && !command.className().isBlank()
                ? ClassCategory.fromString(command.className()) : null;

        List<MemberData> result = gateway.findByFilterAndPagination(
                new Filter(unitEntity, classCategoryEnum, command.memberName()), command.pagination()
        );

        List<MemberData> hashedResult = result.stream().map(member -> {
            member.setCpf(Cpf.of(hasherGateway.decrypt(member.getCpf().getNumber())));

            if (member.getMedicalData() != null) {
                if (member.getMedicalData().getCpf() != null) member.getMedicalData()
                        .setCpf(Cpf.of(hasherGateway.decrypt(member.getMedicalData().getCpf().getNumber())));
                if (member.getMedicalData().getCns() != null) member.getMedicalData()
                        .setCns(Cns.of(hasherGateway.decrypt(member.getMedicalData().getCns().getNumber())));
            }
            if (member.getAddress() != null) {
                if (member.getAddress().getHouseNumber() != null) member.getAddress()
                        .setHouseNumber(hasherGateway.decrypt(member.getAddress().getHouseNumber()));
                if (member.getAddress().getReferenceHouse() != null) member.getAddress()
                        .setReferenceHouse(hasherGateway.decrypt(member.getAddress().getReferenceHouse()));
            }

            return member;
        }).toList();

        return new Pair<>(hashedResult, command.pagination());
    }
}
