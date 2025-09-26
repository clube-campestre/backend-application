package com.campestre.clube.backend_application.infrastructure.persistence.jpa.medicaldata;

import com.campestre.clube.backend_application.core.adapter.MedicalDataGateway;
import org.springframework.stereotype.Repository;

@Repository
public class MedicalDataJpaAdapter implements MedicalDataGateway {

    private final MedicalDataJpaRepository repository;

    public MedicalDataJpaAdapter(MedicalDataJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean existsByCns(String cns) {
        return repository.existsByCns(cns);
    }

    @Override
    public boolean existsByCnsAndCpfNot(String cns, String cpf) {
        return repository.existsByCnsAndCpfNot(cns, cpf);
    }
}
