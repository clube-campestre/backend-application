package com.campestre.clube.backend_application.service;

import com.campestre.clube.backend_application.controller.dtos.requests.MemberDataDtoRequest;
import com.campestre.clube.backend_application.controller.dtos.requests.SaveMedicalDataRequestDto;
import com.campestre.clube.backend_application.controller.mapper.MedicalDataMapper;
import com.campestre.clube.backend_application.exceptions.*;
import com.campestre.clube.backend_application.entity.MedicalData;
import com.campestre.clube.backend_application.repository.MedicalDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MedicalDataService {

    @Autowired
    private MedicalDataRepository medicalDataRepository;

    public MedicalData register(MedicalData medicalData) {
        return medicalDataRepository.save(medicalData);
    }

    public MedicalData createMedicalData(SaveMedicalDataRequestDto dto) {
        MedicalData medicalData = MedicalDataMapper.toEntity(dto);
        if (medicalData.getCpf() != null && medicalDataRepository.existsById(medicalData.getCpf()))
            throw new ConflictException("Medical data with this CPF [%s] already exists".formatted(medicalData.getCpf()));

        return medicalData;
    }

    public MedicalData getById(String cpf) {
        Optional<MedicalData> medicalData = medicalDataRepository.findById(cpf);
        medicalDataNotFoundValidation(medicalData, cpf);

        return medicalData.get();
    }

    public MedicalData update(String cpf, SaveMedicalDataRequestDto dto){
        MedicalData newMedicalData = MedicalDataMapper.toEntity(dto);
        Optional<MedicalData> medicalData = medicalDataRepository.findById(cpf);
        medicalDataNotFoundValidation(medicalData, cpf);

        newMedicalData.setCpf(cpf);
        return newMedicalData;
    }

    public void delete(String cpf){
        if(!medicalDataRepository.existsById(cpf))
            throw new NotFoundException("Medical data by CPF [%s] not found".formatted(cpf));

        medicalDataRepository.deleteById(cpf);
    }


    private void medicalDataNotFoundValidation(Optional<MedicalData> medicalData, String cpf) {
        if (medicalData.isEmpty())
            throw new NotFoundException("Medical data by CPF [%s] not found".formatted(cpf));
    }
}
