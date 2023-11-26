package com.prescription.prescriptioncreator.model;

public class PreviousVisit {
    public String getPreviousVisit() {
        return previousVisit;
    }

    public void setPreviousVisit(String previousVisit) {
        this.previousVisit = previousVisit;
    }

    private String nextVisit;
    private float weight;
    private float height;
    private String bp;
    private float pulse;

    public String getBp() {return bp;}

    public void setBp(String bp) {this.bp = bp;}

    public float getPulse() {return pulse;}

    public void setPulse(float pulse) {this.pulse = pulse;}

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

    public String getNextVisit() {
        return nextVisit;
    }

    public void setNextVisit(String nextVisit) {
        this.nextVisit = nextVisit;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}
