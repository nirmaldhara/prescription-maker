package com.prescription.prescriptioncreator.model;

public class PreviousVisit {
    public String getPreviousVisit() {
        return previousVisit;
    }

    public void setPreviousVisit(String previousVisit) {
        this.previousVisit = previousVisit;
    }

    
    private String previousVisit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private  Integer id;

    public Integer getVisitId() {
        return visitId;
    }

    public void setVisitId(Integer visitId) {
        this.visitId = visitId;
    }

    private  Integer visitId;
}
