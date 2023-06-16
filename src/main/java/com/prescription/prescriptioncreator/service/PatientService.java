package com.prescription.prescriptioncreator.service;

import com.prescription.prescriptioncreator.model.PatientDetails;

import java.sql.SQLException;
import java.util.List;
public interface PatientService {
    public  List<PatientDetails> searchPatientDetails(String mobile_no, String patient_id) throws Exception;
    void addPatient(PatientDetails patientDetails) throws Exception;

}
