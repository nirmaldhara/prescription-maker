package com.prescription.prescriptioncreator.model;

public class DiagnosisDetails {
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private String diagnosis;

    public String getDiagnosis() {return diagnosis;}

    public void setDiagnosis(String diagnosis) {this.diagnosis = diagnosis;}

    @Override
    public String toString() {
        return this.getDiagnosis();
    }
}
