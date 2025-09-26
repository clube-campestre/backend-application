package com.campestre.clube.backend_application.infrastructure.persistence.jpa.medicaldata;

import com.campestre.clube.backend_application.core.domain.MedicalData;

import java.util.List;
import java.util.stream.Collectors;

public class MedicalDataEntityMapper {
    public static MedicalDataEntity toEntity(MedicalData domain) {
        if (domain == null) return null;
        MedicalDataEntity entity = new MedicalDataEntity();
        entity.setCpf(domain.getCpf().getNumber());
        entity.setCns(domain.getCns().getNumber());
        entity.setAgreement(domain.getAgreement());
        entity.setBloodType(domain.getBloodType());
        entity.setCatapora(domain.getCatapora());
        entity.setMeningite(domain.getMeningite());
        entity.setHepatite(domain.getHepatite());
        entity.setDengue(domain.getDengue());
        entity.setPneumonia(domain.getPneumonia());
        entity.setMalaria(domain.getMalaria());
        entity.setFebreAmarela(domain.getFebreAmarela());
        entity.setSarampo(domain.getSarampo());
        entity.setTetano(domain.getTetano());
        entity.setVariola(domain.getVariola());
        entity.setCoqueluche(domain.getCoqueluche());
        entity.setDifteria(domain.getDifteria());
        entity.setRinite(domain.getRinite());
        entity.setBronquite(domain.getBronquite());
        entity.setAsma(domain.getAsma());
        entity.setRubeola(domain.getRubeola());
        entity.setColera(domain.getColera());
        entity.setCovid19(domain.getCovid19());
        entity.setH1n1(domain.getH1n1());
        entity.setCaxumba(domain.getCaxumba());
        entity.setOthers(domain.getOthers());
        entity.setHeartProblems(domain.getHeartProblems());
        entity.setDrugAllergy(domain.getDrugAllergy());
        entity.setLactoseAllergy(domain.getLactoseAllergy());
        entity.setDeficiency(domain.getDeficiency());
        entity.setBloodTransfusion(domain.getBloodTransfusion());
        entity.setSkinAllergy(domain.getSkinAllergy().getHaveProblem());
        entity.setSkinAllergyMedications(domain.getSkinAllergy().getMedication());
        entity.setFaintingOrConvulsion(domain.getFaintingOrConvulsion().getHaveProblem());
        entity.setFaintingOrSeizuresMedications(domain.getFaintingOrConvulsion().getMedication());
        entity.setPsychologicalDisorder(domain.getPsychologicalDisorder());
        entity.setAllergy(domain.getAllergy().getHaveProblem());
        entity.setAllergyMedications(domain.getAllergy().getMedication());
        entity.setDiabetic(domain.getDiabetic().getHaveProblem());
        entity.setDiabeticMedications(domain.getDiabetic().getMedication());
        entity.setRecentSeriousInjury(domain.getRecentSeriousInjury());
        entity.setRecentFracture(domain.getRecentFracture());
        entity.setSurgeries(domain.getSurgeries());
        entity.setHospitalizationReasonLast5Years(domain.getHospitalizationReasonLast5Years());
        return entity;
    }

    public static MedicalData toDomain(MedicalDataEntity entity) {
        if (entity == null) return null;
        return MedicalData.of(
                entity.getCpf(),
                entity.getCns(),
                entity.getAgreement(),
                entity.getBloodType(),
                entity.getCatapora(),
                entity.getMeningite(),
                entity.getHepatite(),
                entity.getDengue(),
                entity.getPneumonia(),
                entity.getMalaria(),
                entity.getFebreAmarela(),
                entity.getSarampo(),
                entity.getTetano(),
                entity.getVariola(),
                entity.getCoqueluche(),
                entity.getDifteria(),
                entity.getRinite(),
                entity.getBronquite(),
                entity.getAsma(),
                entity.getRubeola(),
                entity.getColera(),
                entity.getCovid19(),
                entity.getH1n1(),
                entity.getCaxumba(),
                entity.getOthers(),
                entity.getHeartProblems(),
                entity.getDrugAllergy(),
                entity.getLactoseAllergy(),
                entity.getDeficiency(),
                entity.getBloodTransfusion(),
                entity.getSkinAllergy(),
                entity.getSkinAllergyMedications(),
                entity.getFaintingOrConvulsion(),
                entity.getFaintingOrSeizuresMedications(),
                entity.getPsychologicalDisorder(),
                entity.getAllergy(),
                entity.getAllergyMedications(),
                entity.getDiabetic(),
                entity.getDiabeticMedications(),
                entity.getRecentSeriousInjury(),
                entity.getRecentFracture(),
                entity.getSurgeries(),
                entity.getHospitalizationReasonLast5Years()
        );
    }

    public static List<MedicalData> toDomain(List<MedicalDataEntity> entities) {
        if (entities == null) return null;
        return entities.stream().map(MedicalDataEntityMapper::toDomain).collect(Collectors.toList());
    }
}
