package com.campestre.clube.backend_application.infrastructure.di;

import com.campestre.clube.backend_application.core.adapter.HasherGateway;
import com.campestre.clube.backend_application.core.application.memberdata.*;
import com.campestre.clube.backend_application.infrastructure.persistence.jpa.medicaldata.MedicalDataJpaAdapter;
import com.campestre.clube.backend_application.infrastructure.persistence.jpa.memberdata.MemberDataJpaAdapter;
import com.campestre.clube.backend_application.infrastructure.persistence.jpa.unit.UnitJpaAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MemberDataBeanConfig {

    @Bean
    public SaveMemberDataUseCase saveMemberDataUseCase(
            MemberDataJpaAdapter adapter, MedicalDataJpaAdapter medicalDataAdapter, UnitJpaAdapter unitAdapter,
            HasherGateway hasherGateway
    ) {
        return new SaveMemberDataUseCase(adapter, medicalDataAdapter, unitAdapter, hasherGateway);
    }

    @Bean
    public UpdateMemberDataUseCase updateMemberDataUseCase(
            MemberDataJpaAdapter adapter, MedicalDataJpaAdapter medicalDataAdapter, UnitJpaAdapter unitAdapter,
            HasherGateway hasherGateway
    ) {
        return new UpdateMemberDataUseCase(adapter, medicalDataAdapter, unitAdapter, hasherGateway);
    }

    @Bean
    public DeleteMemberDataUseCase deleteMemberDataUseCase(MemberDataJpaAdapter adapter, HasherGateway hasherGateway) {
        return new DeleteMemberDataUseCase(adapter, hasherGateway);
    }

    @Bean
    public GetMemberDataByCpfUseCase getMemberDataByCpfUseCase(
            MemberDataJpaAdapter adapter, HasherGateway hasherGateway
    ) {
        return new GetMemberDataByCpfUseCase(adapter, hasherGateway);
    }

    @Bean
    public ListMemberDataByClassCategoryAndPaginationUseCase listMemberDataByClassCategoryAndPaginationUseCase(
            MemberDataJpaAdapter adapter, HasherGateway hasherGateway
    ) {
        return new ListMemberDataByClassCategoryAndPaginationUseCase(adapter, hasherGateway);
    }

    @Bean
    public ListMemberDataByFilterAndPaginationUseCase listMemberDataByFilterAndPaginationUseCase(
            MemberDataJpaAdapter adapter, UnitJpaAdapter unitAdapter, HasherGateway hasherGateway
    ) {
        return new ListMemberDataByFilterAndPaginationUseCase(adapter, unitAdapter, hasherGateway);
    }

    @Bean
    public ListMemberDataByUnitNameAndPaginationUseCase listMemberDataByUnitIdAndPaginationUseCase(
            MemberDataJpaAdapter adapter, UnitJpaAdapter unitAdapter, HasherGateway hasherGateway
    ) {
        return new ListMemberDataByUnitNameAndPaginationUseCase(adapter, unitAdapter, hasherGateway);
    }

    @Bean
    public ListMemberDataUseCase listMemberDataUseCase(MemberDataJpaAdapter adapter, HasherGateway hasherGateway) {
        return new ListMemberDataUseCase(adapter, hasherGateway);
    }
}
