package com.prescription.prescriptioncreator.service;

import com.prescription.prescriptioncreator.model.MedicineDetails;
import com.prescription.prescriptioncreator.model.PrescriptionDetails;
import com.prescription.prescriptioncreator.model.PreviousVisit;

import java.sql.Date;
import java.util.List;

public interface PrescriptionService {

    long saveNPrintPrescription( List<MedicineDetails> lstMedicineDetails, int patientId) throws Exception;
    long saveVisitHistory(long patient_id,long visit_id, Date visitDate, Date nextVisitDate,float weight,float height,String bp, float pulse) throws Exception;
    List<MedicineDetails>  getPrescriptionDetailsByVisitId( Integer visitID) throws Exception;
    public List<PreviousVisit> getVisitDetails(int patientId) throws Exception;

}
