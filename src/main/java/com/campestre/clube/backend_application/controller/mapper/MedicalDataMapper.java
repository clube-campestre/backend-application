package com.campestre.clube.backend_application.controller.mapper;

import com.campestre.clube.backend_application.controller.dtos.requests.SaveMedicalDataRequestDto;
import com.campestre.clube.backend_application.controller.dtos.responses.SaveMedicalDataResponseDto;
import com.campestre.clube.backend_application.entity.MedicalData;

public class MedicalDataMapper {

    public static MedicalData toEntity(SaveMedicalDataRequestDto dto) {
        MedicalData medicalData = new MedicalData();

        medicalData.setCpf(dto.getCpf());
        medicalData.setCns(dto.getCns());
        medicalData.setAgreement(dto.getAgreement());
        medicalData.setBloodType(dto.getBloodType());

        medicalData.setCatapora(dto.getCatapora());
        medicalData.setMeningite(dto.getMeningite());
        medicalData.setHepatite(dto.getHepatite());
        medicalData.setDengue(dto.getDengue());
        medicalData.setPneumonia(dto.getPneumonia());
        medicalData.setMalaria(dto.getMalaria());
        medicalData.setFebreAmarela(dto.getFebreAmarela());
        medicalData.setSarampo(dto.getSarampo());
        medicalData.setTetano(dto.getTetano());
        medicalData.setVariola(dto.getVariola());
        medicalData.setCoqueluche(dto.getCoqueluche());
        medicalData.setDifteria(dto.getDifteria());
        medicalData.setRinite(dto.getRinite());
        medicalData.setBronquite(dto.getBronquite());
        medicalData.setAsma(dto.getAsma());
        medicalData.setRubeola(dto.getRubeola());
        medicalData.setColera(dto.getColera());
        medicalData.setCovid19(dto.getCovid19());
        medicalData.setH1n1(dto.getH1n1());
        medicalData.setCaxumba(dto.getCaxumba());
        medicalData.setOthers(dto.getOthers());

        medicalData.setHeartProblems(dto.getHeartProblems());
        medicalData.setDrugAllergy(dto.getDrugAllergy());
        medicalData.setLactoseAllergy(dto.getLactoseAllergy());
        medicalData.setDeficiency(dto.getDeficiency());
        medicalData.setBloodTransfusion(dto.getBloodTransfusion());
        medicalData.setSkinAllergy(dto.getSkinAllergy());
        medicalData.setSkinAllergyMedications(dto.getSkinAllergyMedications());
        medicalData.setFaintingOrConvulsion(dto.getFaintingOrConvulsion());
        medicalData.setFaintingOrSeizuresMedications(dto.getFaintingOrSeizuresMedications());
        medicalData.setPsychologicalDisorder(dto.getPsychologicalDisorder());
        medicalData.setAllergy(dto.getAllergy());
        medicalData.setAllergyMedications(dto.getAllergyMedications());
        medicalData.setDiabetic(dto.getDiabetic());
        medicalData.setDiabeticMedications(dto.getDiabeticMedications());
        medicalData.setRecentSeriousInjury(dto.getRecentSeriousInjury());
        medicalData.setRecentFracture(dto.getRecentFracture());
        medicalData.setSurgeries(dto.getSurgeries());
        medicalData.setHospitalizationReasonLast5Years(dto.getHospitalizationReasonLast5Years());

        return medicalData;
    }

    public static SaveMedicalDataResponseDto toResponse(MedicalData medicalData) {
        SaveMedicalDataResponseDto response = new SaveMedicalDataResponseDto();

        response.setCpf(medicalData.getCpf());
        response.setCns(medicalData.getCns());
        response.setAgreement(medicalData.getAgreement());
        response.setBloodType(medicalData.getBloodType());

        response.setCatapora(medicalData.getCatapora());
        response.setMeningite(medicalData.getMeningite());
        response.setHepatite(medicalData.getHepatite());
        response.setDengue(medicalData.getDengue());
        response.setPneumonia(medicalData.getPneumonia());
        response.setMalaria(medicalData.getMalaria());
        response.setFebreAmarela(medicalData.getFebreAmarela());
        response.setSarampo(medicalData.getSarampo());
        response.setTetano(medicalData.getTetano());
        response.setVariola(medicalData.getVariola());
        response.setCoqueluche(medicalData.getCoqueluche());
        response.setDifteria(medicalData.getDifteria());
        response.setRinite(medicalData.getRinite());
        response.setBronquite(medicalData.getBronquite());
        response.setAsma(medicalData.getAsma());
        response.setRubeola(medicalData.getRubeola());
        response.setColera(medicalData.getColera());
        response.setCovid19(medicalData.getCovid19());
        response.setH1n1(medicalData.getH1n1());
        response.setCaxumba(medicalData.getCaxumba());
        response.setOthers(medicalData.getOthers());

        response.setHeartProblems(medicalData.getHeartProblems());
        response.setDrugAllergy(medicalData.getDrugAllergy());
        response.setLactoseAllergy(medicalData.getLactoseAllergy());
        response.setDeficiency(medicalData.getDeficiency());
        response.setBloodTransfusion(medicalData.getBloodTransfusion());
        response.setSkinAllergy(medicalData.getSkinAllergy());
        response.setSkinAllergyMedications(medicalData.getSkinAllergyMedications());
        response.setFaintingOrConvulsion(medicalData.getFaintingOrConvulsion());
        response.setFaintingOrSeizuresMedications(medicalData.getFaintingOrSeizuresMedications());
        response.setPsychologicalDisorder(medicalData.getPsychologicalDisorder());
        response.setAllergy(medicalData.getAllergy());
        response.setAllergyMedications(medicalData.getAllergyMedications());
        response.setDiabetic(medicalData.getDiabetic());
        response.setDiabeticMedications(medicalData.getDiabeticMedications());
        response.setRecentSeriousInjury(medicalData.getRecentSeriousInjury());
        response.setRecentFracture(medicalData.getRecentFracture());
        response.setSurgeries(medicalData.getSurgeries());
        response.setHospitalizationReasonLast5Years(medicalData.getHospitalizationReasonLast5Years());

        return response;
    }
}