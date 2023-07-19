package com.prescription.prescriptioncreator.Dao;

import com.prescription.prescriptioncreator.model.PatientDetails;

import java.sql.SQLException;
import java.util.List;

public interface PatientDao {
    public List<PatientDetails> searchPatientDetails(String mobile_no, String patient_id) throws Exception;
    public  boolean addPatient(PatientDetails patientDetails) throws Exception;
}
