package com.campestre.clube.backend_application.controller.mapper;

import com.campestre.clube.backend_application.controller.dtos.requests.SaveMedicalDataRequestDto;
import com.campestre.clube.backend_application.controller.dtos.responses.MedicalDataResponseDto;
import com.campestre.clube.backend_application.entity.MedicalData;

public class MedicalDataMapper {
    public static MedicalData toEntity(SaveMedicalDataRequestDto dto) {
        return new MedicalData(
                dto.getCpf(), dto.getCns(), dto.getAgreement(), dto.getBloodType(), dto.getCatapora(),
                dto.getMeningite(), dto.getHepatite(), dto.getDengue(), dto.getPneumonia(), dto.getMalaria(),
                dto.getFebreAmarela(), dto.getSarampo(), dto.getTetano(), dto.getVariola(), dto.getCoqueluche(),
                dto.getDifteria(), dto.getRinite(), dto.getBronquite(), dto.getAsma(), dto.getRubeola(),
                dto.getColera(), dto.getCovid19(), dto.getH1n1(), dto.getCaxumba(), dto.getOthers(),
                dto.getHeartProblems(), dto.getDrugAllergy(), dto.getLactoseAllergy(), dto.getDeficiency(),
                dto.getBloodTransfusion(), dto.getSkinAllergy(), dto.getSkinAllergyMedications(),
                dto.getFaintingOrConvulsion(), dto.getFaintingOrSeizuresMedications(), dto.getPsychologicalDisorder(),
                dto.getAllergy(), dto.getAllergyMedications(), dto.getDiabetic(), dto.getDiabeticMedications(),
                dto.getRecentSeriousInjury(), dto.getRecentFracture(), dto.getSurgeries(),
                dto.getHospitalizationReasonLast5Years()
        );
    }

    public static MedicalDataResponseDto toResponse(MedicalData medicalData) {
        return new MedicalDataResponseDto(
                medicalData.getCpf(), medicalData.getCns(), medicalData.getAgreement(), medicalData.getBloodType(),
                medicalData.getCatapora(), medicalData.getMeningite(), medicalData.getHepatite(),
                medicalData.getDengue(), medicalData.getPneumonia(), medicalData.getMalaria(),
                medicalData.getFebreAmarela(), medicalData.getSarampo(), medicalData.getTetano(),
                medicalData.getVariola(), medicalData.getCoqueluche(), medicalData.getDifteria(),
                medicalData.getRinite(), medicalData.getBronquite(), medicalData.getAsma(), medicalData.getRubeola(),
                medicalData.getColera(), medicalData.getCovid19(), medicalData.getH1n1(), medicalData.getCaxumba(),
                medicalData.getOthers(), medicalData.getHeartProblems(), medicalData.getDrugAllergy(),
                medicalData.getLactoseAllergy(), medicalData.getDeficiency(), medicalData.getBloodTransfusion(),
                medicalData.getSkinAllergy(), medicalData.getSkinAllergyMedications(),
                medicalData.getFaintingOrConvulsion(), medicalData.getFaintingOrSeizuresMedications(),
                medicalData.getPsychologicalDisorder(), medicalData.getAllergy(), medicalData.getAllergyMedications(),
                medicalData.getDiabetic(), medicalData.getDiabeticMedications(), medicalData.getRecentSeriousInjury(),
                medicalData.getRecentFracture(), medicalData.getSurgeries(),
                medicalData.getHospitalizationReasonLast5Years()
        );
    }
}
