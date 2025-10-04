package com.campestre.clube.backend_application.core.application.memberdata;

import com.campestre.clube.backend_application.core.adapter.HasherGateway;
import com.campestre.clube.backend_application.core.adapter.MemberDataGateway;
import com.campestre.clube.backend_application.core.adapter.UnitGateway;
import com.campestre.clube.backend_application.core.application.memberdata.command.ListMemberDataByUnitIdAndPaginationCommand;
import com.campestre.clube.backend_application.core.domain.MemberData;
import com.campestre.clube.backend_application.core.domain.MemberDataForUnit;
import com.campestre.clube.backend_application.core.domain.Unit;
import com.campestre.clube.backend_application.core.domain.enums.UnitRole;
import com.campestre.clube.backend_application.core.domain.valueobject.Cns;
import com.campestre.clube.backend_application.core.domain.valueobject.Cpf;

import java.util.List;

import static com.campestre.clube.backend_application.core.exceptions.ExceptionExtensions.*;

public class ListMemberDataByUnitNameAndPaginationUseCase {

    private final MemberDataGateway gateway;
    private final UnitGateway unitGateway;
    private final HasherGateway hasherGateway;

    public ListMemberDataByUnitNameAndPaginationUseCase(
            MemberDataGateway gateway, UnitGateway unitGateway, HasherGateway hasherGateway
    ) {
        this.gateway = gateway;
        this.unitGateway = unitGateway;
        this.hasherGateway = hasherGateway;
    }

    public MemberDataForUnit execute(ListMemberDataByUnitIdAndPaginationCommand command) {
        if (!unitGateway.existsBySurnameIgnoreCase(command.unitName())) throw NOT_FOUND_UNIT;

        Unit unit = unitGateway.findBySurnameIgnoreCase(command.unitName());
        List<MemberData> counselors = gateway.findByUnitNameAndUnitRole(command.unitName(), UnitRole.CONSELHEIRO);

        if (counselors.isEmpty()) throw BAD_REQUEST_UNIT_MUST_HAVE_COUNSELOR;
        if (counselors.size() > 1) throw BAD_REQUEST_UNIT_MUST_HAVE_ONLY_COUNSELOR;

        List<MemberData> result = gateway.findByUnitNameAndPagination(command.unitName(), command.pagination());

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

        return MemberDataForUnit.of(
                unit.getScore(),
                counselors.getFirst().getUsername(),
                command.pagination().getPageNumber(),
                command.pagination().getPageSize(),
                command.pagination().getTotalItems(),
                command.pagination().getTotalPages(),
                hashedResult
        );
    }
}
