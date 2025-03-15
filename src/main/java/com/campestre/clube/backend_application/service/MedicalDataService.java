package com.campestre.clube.backend_application.service;

import com.campestre.clube.backend_application.exceptions.*;
import com.campestre.clube.backend_application.model.MedicalData;
import com.campestre.clube.backend_application.repository.MedicalDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MedicalDataService {

    @Autowired
    private MedicalDataRepository medicalDataRepository;

    public MedicalData register(MedicalData medicalData) {
        if (medicalDataRepository.existsById(medicalData.getId()))
            throw new MedicalDataConfictException(medicalData.getId());

        return medicalDataRepository.save(medicalData);
    }

    public MedicalData getById(Integer id) {
        Optional<MedicalData> medicalData = medicalDataRepository.findById(id);
        medicalDataNotFoundValidation(medicalData, id);

        return medicalData.get();
    }

    public MedicalData update(Integer id, MedicalData newMedicalData){
        Optional<MedicalData> medicalData = medicalDataRepository.findById(id);
        medicalDataNotFoundValidation(medicalData, id);

        medicalData.get().setCatapora(newMedicalData.getCatapora());
        medicalData.get().setMeningite(newMedicalData.getMeningite());
        medicalData.get().setHepatite(newMedicalData.getHepatite());
        medicalData.get().setDengue(newMedicalData.getDengue());
        medicalData.get().setPneumonia(newMedicalData.getPneumonia());
        medicalData.get().setMalaria(newMedicalData.getMalaria());
        medicalData.get().setFebreAmarela(newMedicalData.getFebreAmarela());
        medicalData.get().setSarampo(newMedicalData.getSarampo());
        medicalData.get().setTetano(newMedicalData.getTetano());
        medicalData.get().setVariola(newMedicalData.getVariola());
        medicalData.get().setCoqueluche(newMedicalData.getCoqueluche());
        medicalData.get().setDifteria(newMedicalData.getDifteria());
        medicalData.get().setRinite(newMedicalData.getRinite());
        medicalData.get().setBronquite(newMedicalData.getBronquite());
        medicalData.get().setAsma(newMedicalData.getAsma());
        medicalData.get().setRubeola(newMedicalData.getRubeola());
        medicalData.get().setColera(newMedicalData.getColera());
        medicalData.get().setCovid19(newMedicalData.getCovid19());
        medicalData.get().setH1n1(newMedicalData.getH1n1());
        medicalData.get().setCaxumba(newMedicalData.getCaxumba());
        medicalData.get().setOthers(newMedicalData.getOthers());

        medicalData.get().setHeartProblems(newMedicalData.getHeartProblems());
        medicalData.get().setDrugAllergy(newMedicalData.getDrugAllergy());
        medicalData.get().setLactoseAllergy(newMedicalData.getLactoseAllergy());
        medicalData.get().setDeficiency(newMedicalData.getDeficiency());
        medicalData.get().setBloodTransfusion(newMedicalData.getBloodTransfusion());
        medicalData.get().setSkinAllergy(newMedicalData.getSkinAllergy());
        medicalData.get().setSkinAllergyMedications(newMedicalData.getSkinAllergyMedications());
        medicalData.get().setFaintingOrConvulsion(newMedicalData.getFaintingOrConvulsion());
        medicalData.get().setFaintingOrSeizuresMedications(newMedicalData.getFaintingOrSeizuresMedications());
        medicalData.get().setPsychologicalDisorder(newMedicalData.getPsychologicalDisorder());
        medicalData.get().setAllergy(newMedicalData.getAllergy());
        medicalData.get().setAllergyMedications(newMedicalData.getAllergyMedications());
        medicalData.get().setDiabetic(newMedicalData.getDiabetic());
        medicalData.get().setDiabeticMedications(newMedicalData.getDiabeticMedications());
        medicalData.get().setRecentSeriousInjury(newMedicalData.getRecentSeriousInjury());
        medicalData.get().setRecentFracture(newMedicalData.getRecentFracture());
        medicalData.get().setSurgeries(newMedicalData.getSurgeries());
        medicalData.get().setHospitalizationReasonLast5Years(newMedicalData.getHospitalizationReasonLast5Years());
        return medicalDataRepository.save(medicalData.get());
    }

    public void delete(Integer id){
        if(!medicalDataRepository.existsById(id))
            throw new MedicalDataNotFoundException(id);

        medicalDataRepository.deleteById(id);
    }



    private void medicalDataNotFoundValidation(Optional<MedicalData> medicalData, Integer id) {
        if (medicalData.isEmpty())
            throw new MedicalDataNotFoundException(id);
    }
}
