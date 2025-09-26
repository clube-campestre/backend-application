package com.campestre.clube.backend_application.infrastructure.di;

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
            MemberDataJpaAdapter adapter, MedicalDataJpaAdapter medicalDataAdapter, UnitJpaAdapter unitAdapter
    ) {
        return new SaveMemberDataUseCase(adapter, medicalDataAdapter, unitAdapter);
    }

    @Bean
    public UpdateMemberDataUseCase updateMemberDataUseCase(
            MemberDataJpaAdapter adapter, MedicalDataJpaAdapter medicalDataAdapter, UnitJpaAdapter unitAdapter
    ) {
        return new UpdateMemberDataUseCase(adapter, medicalDataAdapter, unitAdapter);
    }

    @Bean
    public DeleteMemberDataUseCase deleteMemberDataUseCase(MemberDataJpaAdapter adapter) {
        return new DeleteMemberDataUseCase(adapter);
    }

    @Bean
    public GetMemberDataByCpfUseCase getMemberDataByCpfUseCase(MemberDataJpaAdapter adapter) {
        return new GetMemberDataByCpfUseCase(adapter);
    }

    @Bean
    public ListMemberDataByClassCategoryAndPaginationUseCase listMemberDataByClassCategoryAndPaginationUseCase(
            MemberDataJpaAdapter adapter
    ) {
        return new ListMemberDataByClassCategoryAndPaginationUseCase(adapter);
    }

    @Bean
    public ListMemberDataByFilterAndPaginationUseCase listMemberDataByFilterAndPaginationUseCase(
            MemberDataJpaAdapter adapter, UnitJpaAdapter unitAdapter
    ) {
        return new ListMemberDataByFilterAndPaginationUseCase(adapter, unitAdapter);
    }

    @Bean
    public ListMemberDataByUnitNameAndPaginationUseCase listMemberDataByUnitIdAndPaginationUseCase(
            MemberDataJpaAdapter adapter, UnitJpaAdapter unitAdapter
    ) {
        return new ListMemberDataByUnitNameAndPaginationUseCase(adapter, unitAdapter);
    }

    @Bean
    public ListMemberDataUseCase listMemberDataUseCase(MemberDataJpaAdapter adapter) {
        return new ListMemberDataUseCase(adapter);
    }
}
