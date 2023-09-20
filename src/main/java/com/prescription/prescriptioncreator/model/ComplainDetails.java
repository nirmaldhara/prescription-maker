package com.prescription.prescriptioncreator.model;

public class ComplainDetails {
    private int id;
    private String complain;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComplain() {
        return complain;
    }

    public void setComplain(String complain) {
        this.complain = complain;
    }
    @Override
    public String toString() {return this.getComplain();
    }
}
