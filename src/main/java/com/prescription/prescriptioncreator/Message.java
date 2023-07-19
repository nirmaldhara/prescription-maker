package com.prescription.prescriptioncreator;

public enum Message {
    PATIENT_FNAME_BlANK("Please provide the first name"),
    PATIENT_LNAME_BlANK("Please provide the last name"),
    PATIENT_AGE_BlANK("Please provide the age"),
    PATIENT_SEX_BLANK("Please select the gender Male/Female"),
    ADD_PATIENT_SUCCESS("Patient details added successfully");
    private String value;

    public String val() {
        return value;
    }

    Message(String value){
        this.value = value;
    }
}
