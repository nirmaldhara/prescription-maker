package com.prescription.prescriptioncreator.model;

public class PrescriptionDetails {
    private  String medicineName;
    private  String dose1;
    private  String dose2;
    private  String dose3;
    private  String dose4;
    private  String dose5;
    private  String dose6;
    private String when_bf_af;

    public int getNo_of_days() {
        return no_of_days;
    }

    public void setNo_of_days(int no_of_days) {
        this.no_of_days = no_of_days;
    }

    private int no_of_days;
    public String getWhen_bf_af() {
        return when_bf_af;
    }

    public void setWhen_bf_af(String when_bf_af) {
        this.when_bf_af = when_bf_af;
    }



    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getDose1() {
        return dose1;
    }

    public void setDose1(String dose1) {
        this.dose1 = dose1;
    }

    public String getDose2() {
        return dose2;
    }

    public void setDose2(String dose2) {
        this.dose2 = dose2;
    }

    public String getDose3() {
        return dose3;
    }

    public void setDose3(String dose3) {
        this.dose3 = dose3;
    }

    public String getDose4() {
        return dose4;
    }

    public void setDose4(String dose4) {
        this.dose4 = dose4;
    }

    public String getDose5() {
        return dose5;
    }

    public void setDose5(String dose5) {
        this.dose5 = dose5;
    }

    public String getDose6() {
        return dose6;
    }

    public void setDose6(String dose6) {
        this.dose6 = dose6;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    private  String note;
}
