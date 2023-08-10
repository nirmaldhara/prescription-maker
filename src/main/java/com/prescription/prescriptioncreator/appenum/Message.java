package com.prescription.prescriptioncreator.appenum;

public enum Message {
    PATIENT_FNAME_BlANK("Please provide the first name"),
    PATIENT_LNAME_BlANK("Please provide the last name"),
    PATIENT_AGE_BlANK("Please provide the age"),
    PATIENT_SEX_BLANK("Please select the gender Male/Female"),
    ADD_PATIENT_SUCCESS("Patient details added successfully"),
    ADD_PATIENT_ERROR("Can't add patient details"),
    MOBILE_OR_PATIENT_ID_BLANK("Please provide mobile number or patient id"),
    MEDICINE_NAME_BLANK("Please provide medicine name"),
    ADD_MEDICINE_SUCCESS("Medicine details added successfully"),
    ADD_MEDICINE_ERROR("Can't add medincine details");
    private String value;

    public String val() {
        return value;
    }

    Message(String value){
        this.value = value;
    }
}