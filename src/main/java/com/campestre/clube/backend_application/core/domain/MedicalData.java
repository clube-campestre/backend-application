package com.campestre.clube.backend_application.core.domain;

import com.campestre.clube.backend_application.core.domain.valueobject.Cns;
import com.campestre.clube.backend_application.core.domain.valueobject.Cpf;
import com.campestre.clube.backend_application.core.domain.valueobject.MedicalProblem;

public class MedicalData {
    private Cpf cpf;
    private Cns cns;
    private String agreement;
    private String bloodType;

    private Boolean catapora; //chickenpox
    private Boolean meningite; //meningitis
    private Boolean hepatite; //hepatitis
    private Boolean dengue; //dengueFever
    private Boolean pneumonia; //pneumonia
    private Boolean malaria; //malaria
    private Boolean febreAmarela; //yellowFever
    private Boolean sarampo; //measles
    private Boolean tetano; //tetanus
    private Boolean variola; //smallpox
    private Boolean coqueluche; //whoopingCough
    private Boolean difteria; //diphtheria
    private Boolean rinite; //rhinitis
    private Boolean bronquite; //bronchitis
    private Boolean asma; //asthma
    private Boolean rubeola; //rubella
    private Boolean colera; //cholera
    private Boolean covid19; //covid19
    private Boolean h1n1; //h1n1
    private Boolean caxumba; //mumps

    private String others;
    private String heartProblems;
    private String drugAllergy;
    private Boolean lactoseAllergy;
    private String deficiency;
    private Boolean bloodTransfusion;
    private MedicalProblem skinAllergy;
    private MedicalProblem faintingOrConvulsion;
    private String psychologicalDisorder;
    private MedicalProblem allergy;
    private MedicalProblem diabetic;
    private Boolean recentSeriousInjury;
    private String recentFracture;
    private String surgeries;
    private String hospitalizationReasonLast5Years;

    private MedicalData(
            Cpf cpf, Cns cns, String agreement, String bloodType, Boolean catapora, Boolean meningite,
            Boolean hepatite, Boolean dengue, Boolean pneumonia, Boolean malaria, Boolean febreAmarela, Boolean sarampo,
            Boolean tetano, Boolean variola, Boolean coqueluche, Boolean difteria, Boolean rinite, Boolean bronquite,
            Boolean asma, Boolean rubeola, Boolean colera, Boolean covid19, Boolean h1n1, Boolean caxumba,
            String others, String heartProblems, String drugAllergy, Boolean lactoseAllergy, String deficiency,
            Boolean bloodTransfusion, MedicalProblem skinAllergy, MedicalProblem faintingOrConvulsion,
            String psychologicalDisorder, MedicalProblem allergy, MedicalProblem diabetic, Boolean recentSeriousInjury,
            String recentFracture, String surgeries, String hospitalizationReasonLast5Years
    ) {
        this.cpf = cpf;
        this.cns = cns;
        this.agreement = agreement;
        this.bloodType = bloodType;

        this.catapora = catapora;
        this.meningite = meningite;
        this.hepatite = hepatite;
        this.dengue = dengue;
        this.pneumonia = pneumonia;
        this.malaria = malaria;
        this.febreAmarela = febreAmarela;
        this.sarampo = sarampo;
        this.tetano = tetano;
        this.variola = variola;
        this.coqueluche = coqueluche;
        this.difteria = difteria;
        this.rinite = rinite;
        this.bronquite = bronquite;
        this.asma = asma;
        this.rubeola = rubeola;
        this.colera = colera;
        this.covid19 = covid19;
        this.h1n1 = h1n1;
        this.caxumba = caxumba;

        this.others = others;
        this.heartProblems = heartProblems;
        this.drugAllergy = drugAllergy;
        this.lactoseAllergy = lactoseAllergy;
        this.deficiency = deficiency;
        this.bloodTransfusion = bloodTransfusion;
        this.skinAllergy = skinAllergy;
        this.faintingOrConvulsion = faintingOrConvulsion;
        this.psychologicalDisorder = psychologicalDisorder;
        this.allergy = allergy;
        this.diabetic = diabetic;
        this.recentSeriousInjury = recentSeriousInjury;
        this.recentFracture = recentFracture;
        this.surgeries = surgeries;
        this.hospitalizationReasonLast5Years = hospitalizationReasonLast5Years;
    }

    public static MedicalData of(
            String cpf, String cns, String agreement, String bloodType, Boolean catapora, Boolean meningite,
            Boolean hepatite, Boolean dengue, Boolean pneumonia, Boolean malaria, Boolean febreAmarela, Boolean sarampo,
            Boolean tetano, Boolean variola, Boolean coqueluche, Boolean difteria, Boolean rinite, Boolean bronquite,
            Boolean asma, Boolean rubeola, Boolean colera, Boolean covid19, Boolean h1n1, Boolean caxumba,
            String others, String heartProblems, String drugAllergy, Boolean lactoseAllergy, String deficiency,
            Boolean bloodTransfusion, Boolean haveSkinAllergy, String skinAllergyMedication,
            Boolean haveFaintingOrConvulsion, String faintingOrConvulsionMedication, String psychologicalDisorder,
            Boolean haveAllergy, String allergyMedication, Boolean haveDiabetic, String diabeticMedication,
            Boolean recentSeriousInjury, String recentFracture, String surgeries, String hospitalizationReasonLast5Years
    ) {
        return new MedicalData(
                Cpf.of(cpf), Cns.of(cns), agreement, bloodType,

                catapora, meningite, hepatite, dengue, pneumonia, malaria, febreAmarela, sarampo, tetano, variola,
                coqueluche, difteria, rinite, bronquite, asma, rubeola, colera, covid19, h1n1, caxumba,

                others, heartProblems, drugAllergy, lactoseAllergy, deficiency, bloodTransfusion,
                MedicalProblem.of(
                        haveSkinAllergy,
                        skinAllergyMedication
                ),
                MedicalProblem.of(
                        haveFaintingOrConvulsion,
                        faintingOrConvulsionMedication
                ),
                psychologicalDisorder,
                MedicalProblem.of(
                        haveAllergy,
                        allergyMedication
                ),
                MedicalProblem.of(
                        haveDiabetic,
                        diabeticMedication
                ), recentSeriousInjury, recentFracture,
                surgeries, hospitalizationReasonLast5Years
        );
    }

    public Cpf getCpf() {
        return cpf;
    }

    public Cns getCns() {
        return cns;
    }

    public String getAgreement() {
        return agreement;
    }

    public String getBloodType() {
        return bloodType;
    }

    public Boolean getCatapora() {
        return catapora;
    }

    public Boolean getMeningite() {
        return meningite;
    }

    public Boolean getHepatite() {
        return hepatite;
    }

    public Boolean getDengue() {
        return dengue;
    }

    public Boolean getPneumonia() {
        return pneumonia;
    }

    public Boolean getMalaria() {
        return malaria;
    }

    public Boolean getFebreAmarela() {
        return febreAmarela;
    }

    public Boolean getSarampo() {
        return sarampo;
    }

    public Boolean getTetano() {
        return tetano;
    }

    public Boolean getVariola() {
        return variola;
    }

    public Boolean getCoqueluche() {
        return coqueluche;
    }

    public Boolean getDifteria() {
        return difteria;
    }

    public Boolean getRinite() {
        return rinite;
    }

    public Boolean getBronquite() {
        return bronquite;
    }

    public Boolean getAsma() {
        return asma;
    }

    public Boolean getRubeola() {
        return rubeola;
    }

    public Boolean getColera() {
        return colera;
    }

    public Boolean getCovid19() {
        return covid19;
    }

    public Boolean getH1n1() {
        return h1n1;
    }

    public Boolean getCaxumba() {
        return caxumba;
    }

    public String getOthers() {
        return others;
    }

    public String getHeartProblems() {
        return heartProblems;
    }

    public String getDrugAllergy() {
        return drugAllergy;
    }

    public Boolean getLactoseAllergy() {
        return lactoseAllergy;
    }

    public String getDeficiency() {
        return deficiency;
    }

    public Boolean getBloodTransfusion() {
        return bloodTransfusion;
    }

    public MedicalProblem getSkinAllergy() {
        return skinAllergy;
    }

    public MedicalProblem getFaintingOrConvulsion() {
        return faintingOrConvulsion;
    }

    public String getPsychologicalDisorder() {
        return psychologicalDisorder;
    }

    public MedicalProblem getAllergy() {
        return allergy;
    }

    public MedicalProblem getDiabetic() {
        return diabetic;
    }

    public Boolean getRecentSeriousInjury() {
        return recentSeriousInjury;
    }

    public String getRecentFracture() {
        return recentFracture;
    }

    public String getSurgeries() {
        return surgeries;
    }

    public String getHospitalizationReasonLast5Years() {
        return hospitalizationReasonLast5Years;
    }
}
