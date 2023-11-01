package com.prescription.prescriptioncreator.model;

public class FindingsDetails {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFindings() {
        return findings;
    }

    public void setFindings(String findings) {
        this.findings = findings;
    }

    private String findings;

    @Override
    public String toString() {return this.getFindings();
    }
}
