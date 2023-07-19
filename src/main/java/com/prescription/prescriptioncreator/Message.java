package com.prescription.prescriptioncreator;

public enum Message {
    PATIENT_FNAME_BlANK("Please provide the first name"),
    PATIENT_LNAME_BlANK("Please provide the last name"),
    PATIENT_AGE_BlANK("Please provide the age"),
    PATIENT_SEX_BLANK("Please select the gender Male/Female"),
    ADD_PATIENT_SUCCESS("Patient details added successfully"),
    MEDICINE_NAME_BLANK("Please provide the medicine name"),
    MEDICINE_WHEN_BLANK("Please provide the medicine time BF/AF"),
    MEDICINE_NO_OF_DAYS_BLANK("Please provide the number of days"),
    ADD_MEDICINE_SUCCESS("Medicine details added successfully");

    private String value;

    public String val() {
        return value;
    }

    Message(String value){
        this.value = value;
    }
}
