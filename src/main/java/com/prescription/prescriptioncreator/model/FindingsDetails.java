package com.prescription.prescriptioncreator.model;

public class FindingsDetails {
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private long id;
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
