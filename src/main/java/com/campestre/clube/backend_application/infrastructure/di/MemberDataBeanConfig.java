package com.campestre.clube.backend_application.infrastructure.di;

import com.campestre.clube.backend_application.core.application.memberdata.*;
import com.campestre.clube.backend_application.infrastructure.persistence.jpa.medicaldata.MedicalDataJpaAdapter;
import com.campestre.clube.backend_application.infrastructure.persistence.jpa.memberdata.MemberDataJpaAdapter;
import com.campestre.clube.backend_application.infrastructure.persistence.jpa.unit.UnitJpaAdapter;
import com.campestre.clube.backend_application.infrastructure.security.JasyptHasher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MemberDataBeanConfig {

    @Bean
    public SaveMemberDataUseCase saveMemberDataUseCase(
            MemberDataJpaAdapter adapter, MedicalDataJpaAdapter medicalDataAdapter, UnitJpaAdapter unitAdapter,
            JasyptHasher jasyptHasher
    ) {
        return new SaveMemberDataUseCase(adapter, medicalDataAdapter, unitAdapter, jasyptHasher);
    }

    @Bean
    public UpdateMemberDataUseCase updateMemberDataUseCase(
            MemberDataJpaAdapter adapter, MedicalDataJpaAdapter medicalDataAdapter, UnitJpaAdapter unitAdapter,
            JasyptHasher jasyptHasher
    ) {
        return new UpdateMemberDataUseCase(adapter, medicalDataAdapter, unitAdapter, jasyptHasher);
    }

    @Bean
    public DeleteMemberDataUseCase deleteMemberDataUseCase(MemberDataJpaAdapter adapter, JasyptHasher jasyptHasher) {
        return new DeleteMemberDataUseCase(adapter, jasyptHasher);
    }

    @Bean
    public GetMemberDataByCpfUseCase getMemberDataByCpfUseCase(
            MemberDataJpaAdapter adapter, JasyptHasher jasyptHasher
    ) {
        return new GetMemberDataByCpfUseCase(adapter, jasyptHasher);
    }

    @Bean
    public ListMemberDataByClassCategoryAndPaginationUseCase listMemberDataByClassCategoryAndPaginationUseCase(
            MemberDataJpaAdapter adapter, JasyptHasher jasyptHasher
    ) {
        return new ListMemberDataByClassCategoryAndPaginationUseCase(adapter, jasyptHasher);
    }

    @Bean
    public ListMemberDataByFilterAndPaginationUseCase listMemberDataByFilterAndPaginationUseCase(
            MemberDataJpaAdapter adapter, UnitJpaAdapter unitAdapter, JasyptHasher jasyptHasher
    ) {
        return new ListMemberDataByFilterAndPaginationUseCase(adapter, unitAdapter, jasyptHasher);
    }

    @Bean
    public ListMemberDataByUnitNameAndPaginationUseCase listMemberDataByUnitIdAndPaginationUseCase(
            MemberDataJpaAdapter adapter, UnitJpaAdapter unitAdapter, JasyptHasher jasyptHasher
    ) {
        return new ListMemberDataByUnitNameAndPaginationUseCase(adapter, unitAdapter, jasyptHasher);
    }

    @Bean
    public ListMemberDataUseCase listMemberDataUseCase(MemberDataJpaAdapter adapter, JasyptHasher jasyptHasher) {
        return new ListMemberDataUseCase(adapter, jasyptHasher);
    }
}
