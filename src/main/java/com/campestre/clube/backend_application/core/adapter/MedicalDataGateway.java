package com.campestre.clube.backend_application.core.adapter;

public interface MedicalDataGateway {
    boolean existsByCns(String cpf);
    boolean existsByCnsAndCpfNot(String cns, String cpf);
}
