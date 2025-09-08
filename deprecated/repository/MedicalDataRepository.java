package com.campestre.clube.backend_application.deprecated.repository;

import com.campestre.clube.backend_application.deprecated.entity.MedicalData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalDataRepository extends JpaRepository<MedicalData, String> {
    boolean existsByCpf(String cpf);
    boolean existsByCns(String cns);
}
