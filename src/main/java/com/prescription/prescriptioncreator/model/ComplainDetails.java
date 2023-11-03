package com.prescription.prescriptioncreator.model;

public class ComplainDetails {
    private long id;
    private String complain;



    public String getComplain() {
        return complain;
    }

    public void setComplain(String complain) {
        this.complain = complain;
    }
    @Override
    public String toString() {return this.getComplain();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
