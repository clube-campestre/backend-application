package com.campestre.clube.backend_application.core.application.memberdata;

import com.campestre.clube.backend_application.core.adapter.MemberDataGateway;
import com.campestre.clube.backend_application.core.domain.MemberData;

import java.util.List;

public class ListMemberDataUseCase {

    private final MemberDataGateway gateway;

    public ListMemberDataUseCase(MemberDataGateway gateway) {
        this.gateway = gateway;
    }

    public List<MemberData> execute() {
        return gateway.findAll();
    }
}
