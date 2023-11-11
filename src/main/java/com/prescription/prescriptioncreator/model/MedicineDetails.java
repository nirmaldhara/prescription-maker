package com.prescription.prescriptioncreator.model;

public class MedicineDetails implements Comparable<MedicineDetails>{
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private long id = 0;

    public long getMedicineID() {
        return medicineID;
    }

    public void setMedicineID(long medicineID) {
        this.medicineID = medicineID;
    }

    private long medicineID;
    private String medicineName;
    private String Dose1;
    private String Dose2;
    private String Dose3;
    private String Dose4;
    private String Dose5;
    private String Dose6;


    private String when;
    private int noOfDays;


    public int getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(int noOfDays) {
        this.noOfDays = noOfDays;
    }



    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
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

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    private String note;
    @Override
    public String toString() {return this.getMedicineName();
    }


    @Override
    public int compareTo(MedicineDetails o) {

         return this.getMedicineName().compareTo(o.getMedicineName());
    }
}
