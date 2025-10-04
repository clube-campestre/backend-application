package com.campestre.clube.backend_application.core.application.memberdata;

import com.campestre.clube.backend_application.core.adapter.HasherGateway;
import com.campestre.clube.backend_application.core.adapter.MemberDataGateway;
import com.campestre.clube.backend_application.core.application.memberdata.command.ListMemberDataByClassCategoryAndPaginationCommand;
import com.campestre.clube.backend_application.core.domain.MemberData;
import com.campestre.clube.backend_application.core.domain.MemberDataForClass;
import com.campestre.clube.backend_application.core.domain.enums.ClassRole;
import com.campestre.clube.backend_application.core.domain.valueobject.Cns;
import com.campestre.clube.backend_application.core.domain.valueobject.Cpf;

import java.util.List;

import static com.campestre.clube.backend_application.core.exceptions.ExceptionExtensions.*;

public class ListMemberDataByClassCategoryAndPaginationUseCase {

    private final MemberDataGateway gateway;
    private final HasherGateway hasherGateway;

    public ListMemberDataByClassCategoryAndPaginationUseCase(MemberDataGateway gateway, HasherGateway hasherGateway) {
        this.gateway = gateway;
        this.hasherGateway = hasherGateway;
    }

    public MemberDataForClass execute(ListMemberDataByClassCategoryAndPaginationCommand command) {
        List<MemberData> instructors =
                gateway.findByClassCategoryAndClassRole(command.classCategory(), ClassRole.INSTRUTOR);

        if (instructors.isEmpty()) throw BAD_REQUEST_CLASS_MUST_HAVE_INSTRUCTOR;
        if (instructors.size() > 1) throw BAD_REQUEST_CLASS_MUST_HAVE_ONLY_INSTRUCTOR;

        List<MemberData> result = gateway.findByClassAndPagination(command.classCategory(), command.pagination());

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

        return MemberDataForClass.of(
                instructors.getFirst().getUsername(),
                command.pagination().getPageNumber(),
                command.pagination().getPageSize(),
                command.pagination().getTotalItems(),
                command.pagination().getTotalPages(),
                hashedResult
        );
    }
}
