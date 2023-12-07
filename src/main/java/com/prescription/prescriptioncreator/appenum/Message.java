package com.prescription.prescriptioncreator.appenum;

public enum Message {
    PATIENT_FNAME_BlANK("Please provide the first name"),
    PATIENT_LNAME_BlANK("Please provide the last name"),
    PATIENT_AGE_BlANK("Please provide the age"),
    PATIENT_SEX_BLANK("Please select the gender Male/Female"),
    ADD_PATIENT_SUCCESS("Patient details added successfully"),
    ADD_PATIENT_ERROR("Can't add patient details, already exist"),
    MOBILE_OR_PATIENT_ID_BLANK("Please provide mobile number or patient id"),
    PATIENT_NOT_FOUND("Patient Details not found"),
    MEDICINE_NAME_BLANK("Please provide medicine name"),
    ADD_MEDICINE_SUCCESS("Medicine details added successfully"),
    ADD_MEDICINE_ERROR("Can't add medicine details"),
    ADD_TEST_SUCCESS("Test details added successfully"),
    ADD_TEST_ERROR("Can't add test details"),
    TEST_NAME_BLANK("Please provide test name"),
    TEST_VALUE_BLANK("Please provide test value"),
    PRINT_ERROR("Please select a patient"),
    MEDICINE_NAME_BlANK("Please provide a medicine name"),
    MEDICINE_TIME_BlANK("Select when you have to take the medicine"),
    MEDICINE_DAYS_BLANK("Select no of days to take medicine"),
    PATIENT_MOBILE_NO_MISMATCH("Provide 10 digits valid mobile number"),
    MEDICINE_DOSE_BLANK("Please provide dose1"),
    AGE_IN_INTEGER_BLANK("Enter age or DOB");
    private String value;

    public String val() {
        return value;
    }

    Message(String value){
        this.value = value;
    }
}
