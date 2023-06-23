package com.prescription.prescriptioncreator.model;

public class PrescriptionDetails {
    private int patientid;
    private  String medicineName;
    private String when;
    private String noOfDays;
    private  String Dose1;
    private  String Dose2;
    private  String Dose3;
    private  String Dose4;
    private  String Dose5;
    private  String Dose6;
    private String note;

    public int getPatientid() {
        return patientid;
    }

    public void setPatientid(int patientid) {
        this.patientid = patientid;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public String getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(String noOfDays) {
        this.noOfDays = noOfDays;
    }

    public String getDose1() {
        return Dose1;
    }

    public void setDose1(String dose1) {
        Dose1 = dose1;
    }

    public String getDose2() {
        return Dose2;
    }

    public void setDose2(String dose2) {
        Dose2 = dose2;
    }

    public String getDose3() {
        return Dose3;
    }

    public void setDose3(String dose3) {
        Dose3 = dose3;
    }

    public String getDose4() {
        return Dose4;
    }

    public void setDose4(String dose4) {
        Dose4 = dose4;
    }

    public String getDose5() {
        return Dose5;
    }

    public void setDose5(String dose5) {
        Dose5 = dose5;
    }

    public String getDose6() {
        return Dose6;
    }

    public void setDose6(String dose6) {
        Dose6 = dose6;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
