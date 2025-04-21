package com.campestre.clube.backend_application.controller.dtos.requests;

import com.campestre.clube.backend_application.entity.MedicalData;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public class SaveMedicalDataRequestDto {
    @CPF
    @Size(min = 11, max = 11)
    private String cpf;
    @NotBlank
    @Size(min = 15, max = 15)
    private String cns;
    @NotBlank
    @Size(max = 20)
    private String agreement;
    @NotBlank
    @Size(min = 2, max = 3)
    private String bloodType;

    private Boolean catapora = false;
    private Boolean meningite = false;
    private Boolean hepatite = false;
    private Boolean dengue = false;
    private Boolean pneumonia = false;
    private Boolean malaria = false;
    private Boolean febreAmarela = false;
    private Boolean sarampo = false;
    private Boolean tetano = false;
    private Boolean variola = false;
    private Boolean coqueluche = false;
    private Boolean difteria = false;
    private Boolean rinite = false;
    private Boolean bronquite = false;
    private Boolean asma = false;
    private Boolean rubeola = false;
    private Boolean colera = false;
    private Boolean covid19 = false;
    private Boolean h1n1 = false;
    private Boolean caxumba = false;
    private String others = "";

    private String heartProblems = "";
    private String drugAllergy = "";
    private Boolean lactoseAllergy = false;
    private String deficiency = "";
    private Boolean bloodTransfusion = false;
    private Boolean skinAllergy = false;
    private String skinAllergyMedications = "";
    private Boolean faintingOrConvulsion = false;
    private String faintingOrSeizuresMedications = "";
    private String psychologicalDisorder = "";
    private Boolean allergy = false;
    private String allergyMedications = "";
    private Boolean diabetic = false;
    private String diabeticMedications = "";
    private Boolean recentSeriousInjury = false;
    private String recentFracture = "";
    private String surgeries = "";
    private String hospitalizationReasonLast5Years = "";



    public SaveMedicalDataRequestDto() {}

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCns() {
        return cns;
    }

    public void setCns(String cns) {
        this.cns = cns;
    }

    public String getAgreement() {
        return agreement;
    }

    public void setAgreement(String agreement) {
        this.agreement = agreement;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public Boolean getCatapora() {
        return catapora;
    }

    public void setCatapora(Boolean catapora) {
        this.catapora = catapora;
    }

    public Boolean getMeningite() {
        return meningite;
    }

    public void setMeningite(Boolean meningite) {
        this.meningite = meningite;
    }

    public Boolean getHepatite() {
        return hepatite;
    }

    public void setHepatite(Boolean hepatite) {
        this.hepatite = hepatite;
    }

    public Boolean getDengue() {
        return dengue;
    }

    public void setDengue(Boolean dengue) {
        this.dengue = dengue;
    }

    public Boolean getPneumonia() {
        return pneumonia;
    }

    public void setPneumonia(Boolean pneumonia) {
        this.pneumonia = pneumonia;
    }

    public Boolean getMalaria() {
        return malaria;
    }

    public void setMalaria(Boolean malaria) {
        this.malaria = malaria;
    }

    public Boolean getFebreAmarela() {
        return febreAmarela;
    }

    public void setFebreAmarela(Boolean febreAmarela) {
        this.febreAmarela = febreAmarela;
    }

    public Boolean getSarampo() {
        return sarampo;
    }

    public void setSarampo(Boolean sarampo) {
        this.sarampo = sarampo;
    }

    public Boolean getTetano() {
        return tetano;
    }

    public void setTetano(Boolean tetano) {
        this.tetano = tetano;
    }

    public Boolean getVariola() {
        return variola;
    }

    public void setVariola(Boolean variola) {
        this.variola = variola;
    }

    public Boolean getCoqueluche() {
        return coqueluche;
    }

    public void setCoqueluche(Boolean coqueluche) {
        this.coqueluche = coqueluche;
    }

    public Boolean getDifteria() {
        return difteria;
    }

    public void setDifteria(Boolean difteria) {
        this.difteria = difteria;
    }

    public Boolean getRinite() {
        return rinite;
    }

    public void setRinite(Boolean rinite) {
        this.rinite = rinite;
    }

    public Boolean getBronquite() {
        return bronquite;
    }

    public void setBronquite(Boolean bronquite) {
        this.bronquite = bronquite;
    }

    public Boolean getAsma() {
        return asma;
    }

    public void setAsma(Boolean asma) {
        this.asma = asma;
    }

    public Boolean getRubeola() {
        return rubeola;
    }

    public void setRubeola(Boolean rubeola) {
        this.rubeola = rubeola;
    }

    public Boolean getColera() {
        return colera;
    }

    public void setColera(Boolean colera) {
        this.colera = colera;
    }

    public Boolean getCovid19() {
        return covid19;
    }

    public void setCovid19(Boolean covid19) {
        this.covid19 = covid19;
    }

    public Boolean getH1n1() {
        return h1n1;
    }

    public void setH1n1(Boolean h1n1) {
        this.h1n1 = h1n1;
    }

    public Boolean getCaxumba() {
        return caxumba;
    }

    public void setCaxumba(Boolean caxumba) {
        this.caxumba = caxumba;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public String getHeartProblems() {
        return heartProblems;
    }

    public void setHeartProblems(String heartProblems) {
        this.heartProblems = heartProblems;
    }

    public String getDrugAllergy() {
        return drugAllergy;
    }

    public void setDrugAllergy(String drugAllergy) {
        this.drugAllergy = drugAllergy;
    }

    public Boolean getLactoseAllergy() {
        return lactoseAllergy;
    }

    public void setLactoseAllergy(Boolean lactoseAllergy) {
        this.lactoseAllergy = lactoseAllergy;
    }

    public String getDeficiency() {
        return deficiency;
    }

    public void setDeficiency(String deficiency) {
        this.deficiency = deficiency;
    }

    public Boolean getBloodTransfusion() {
        return bloodTransfusion;
    }

    public void setBloodTransfusion(Boolean bloodTransfusion) {
        this.bloodTransfusion = bloodTransfusion;
    }

    public Boolean getSkinAllergy() {
        return skinAllergy;
    }

    public void setSkinAllergy(Boolean skinAllergy) {
        this.skinAllergy = skinAllergy;
    }

    public String getSkinAllergyMedications() {
        return skinAllergyMedications;
    }

    public void setSkinAllergyMedications(String skinAllergyMedications) {
        this.skinAllergyMedications = skinAllergyMedications;
    }

    public Boolean getFaintingOrConvulsion() {
        return faintingOrConvulsion;
    }

    public void setFaintingOrConvulsion(Boolean faintingOrConvulsion) {
        this.faintingOrConvulsion = faintingOrConvulsion;
    }

    public String getFaintingOrSeizuresMedications() {
        return faintingOrSeizuresMedications;
    }

    public void setFaintingOrSeizuresMedications(String faintingOrSeizuresMedications) {
        this.faintingOrSeizuresMedications = faintingOrSeizuresMedications;
    }

    public String getPsychologicalDisorder() {
        return psychologicalDisorder;
    }

    public void setPsychologicalDisorder(String psychologicalDisorder) {
        this.psychologicalDisorder = psychologicalDisorder;
    }

    public Boolean getAllergy() {
        return allergy;
    }

    public void setAllergy(Boolean allergy) {
        this.allergy = allergy;
    }

    public String getAllergyMedications() {
        return allergyMedications;
    }

    public void setAllergyMedications(String allergyMedications) {
        this.allergyMedications = allergyMedications;
    }

    public Boolean getDiabetic() {
        return diabetic;
    }

    public void setDiabetic(Boolean diabetic) {
        this.diabetic = diabetic;
    }

    public String getDiabeticMedications() {
        return diabeticMedications;
    }

    public void setDiabeticMedications(String diabeticMedications) {
        this.diabeticMedications = diabeticMedications;
    }

    public Boolean getRecentSeriousInjury() {
        return recentSeriousInjury;
    }

    public void setRecentSeriousInjury(Boolean recentSeriousInjury) {
        this.recentSeriousInjury = recentSeriousInjury;
    }

    public String getRecentFracture() {
        return recentFracture;
    }

    public void setRecentFracture(String recentFracture) {
        this.recentFracture = recentFracture;
    }

    public String getSurgeries() {
        return surgeries;
    }

    public void setSurgeries(String surgeries) {
        this.surgeries = surgeries;
    }

    public String getHospitalizationReasonLast5Years() {
        return hospitalizationReasonLast5Years;
    }

    public void setHospitalizationReasonLast5Years(String hospitalizationReasonLast5Years) {
        this.hospitalizationReasonLast5Years = hospitalizationReasonLast5Years;
    }
}
