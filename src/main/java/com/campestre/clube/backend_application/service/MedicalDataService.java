package com.campestre.clube.backend_application.service;

import com.campestre.clube.backend_application.exceptions.*;
import com.campestre.clube.backend_application.entity.MedicalData;
import com.campestre.clube.backend_application.repository.MedicalDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalDataService {

    @Autowired
    private MedicalDataRepository medicalDataRepository;

    public MedicalData save(MedicalData medicalData) {
        if (medicalData.getCpf() == null)
            throw new BadRequestException("Não foi possível salvar os dados médicos: CPF não informado.");

        if (medicalDataRepository.existsByCpf(medicalData.getCpf()))
            throw new ConflictException("Já existem dados médicos cadastrados com o CPF informado.");

        return medicalDataRepository.save(medicalData);
    }

    public MedicalData update(MedicalData medicalData) {
        if (medicalData.getCpf() == null)
            throw new BadRequestException("Não é possível atualizar os dados médicos: CPF não informado.");
        existsByCpfOrThrow(medicalData.getCpf());

        return medicalDataRepository.save(medicalData);
    }

    public MedicalData getById(String cpf) {
        existsByCpfOrThrow(cpf);
        return medicalDataRepository.findById(cpf).get();
    }

    public Boolean existsByCns(String cns){
        return medicalDataRepository.existsByCns(cns);
    }

    public void delete(String cpf){
        existsByCpfOrThrow(cpf);
        medicalDataRepository.deleteById(cpf);
    }



    private void existsByCpfOrThrow(String cpf) {
        if (!medicalDataRepository.existsById(cpf))
            throw new NotFoundException("Não encontramos os dados médicos solicitados.");
    }
}
