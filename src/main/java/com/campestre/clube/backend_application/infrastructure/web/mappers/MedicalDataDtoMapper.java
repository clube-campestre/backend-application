package com.campestre.clube.backend_application.infrastructure.web.mappers;

import com.campestre.clube.backend_application.core.domain.MedicalData;
import com.campestre.clube.backend_application.infrastructure.web.dtos.medicaldata.MedicalDataResponseDto;

public class MedicalDataDtoMapper {

    public static MedicalDataResponseDto toResponse(MedicalData domain) {
        return new MedicalDataResponseDto(
                domain.getCpf().getNumber(),
                domain.getCns().getNumber(),
                domain.getAgreement(),
                domain.getBloodType(),

                domain.getCatapora(),
                domain.getMeningite(),
                domain.getHepatite(),
                domain.getDengue(),
                domain.getPneumonia(),
                domain.getMalaria(),
                domain.getFebreAmarela(),
                domain.getSarampo(),
                domain.getTetano(),
                domain.getVariola(),
                domain.getCoqueluche(),
                domain.getDifteria(),
                domain.getRinite(),
                domain.getBronquite(),
                domain.getAsma(),
                domain.getRubeola(),
                domain.getColera(),
                domain.getCovid19(),
                domain.getH1n1(),
                domain.getCaxumba(),

                domain.getOthers(),
                domain.getHeartProblems(),
                domain.getDrugAllergy(),
                domain.getLactoseAllergy(),
                domain.getDeficiency(),
                domain.getBloodTransfusion(),
                domain.getSkinAllergy().getHaveProblem(),
                domain.getSkinAllergy().getMedication(),
                domain.getFaintingOrConvulsion().getHaveProblem(),
                domain.getFaintingOrConvulsion().getMedication(),
                domain.getPsychologicalDisorder(),
                domain.getAllergy().getHaveProblem(),
                domain.getAllergy().getMedication(),
                domain.getDiabetic().getHaveProblem(),
                domain.getDiabetic().getMedication(),
                domain.getRecentSeriousInjury(),
                domain.getRecentFracture(),
                domain.getSurgeries(),
                domain.getHospitalizationReasonLast5Years()
        );
    }
}