package com.campestre.clube.backend_application.core.adapter;

import com.campestre.clube.backend_application.core.domain.MedicalData;

public interface MedicalDataGateway {
    boolean existsByCpf(String cpf);
    boolean existsByCns(String cns);

    MedicalData findByCpf(String cpf);
    MedicalData findByCns(String cns);

    MedicalData save(MedicalData medicalData);

    void removeByCpf(String cpf);
}
