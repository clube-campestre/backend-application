package com.campestre.clube.backend_application.infrastructure.persistence.jpa.medicaldata;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalDataJpaRepository extends JpaRepository<MedicalDataEntity, String> {
    boolean existsByCns(String cns);
    boolean existsByCnsAndCpfNot(String cns, String cpf);
}
