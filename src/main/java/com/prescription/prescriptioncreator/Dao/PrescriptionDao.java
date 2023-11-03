package com.prescription.prescriptioncreator.Dao;

import com.prescription.prescriptioncreator.model.MedicineDetails;
import com.prescription.prescriptioncreator.model.PrescriptionDetails;
import com.prescription.prescriptioncreator.model.PreviousVisit;

import java.util.List;

public interface PrescriptionDao {
    public long saveNPrintPrescription(List<MedicineDetails> lstMedicineDetails, int patientId) throws Exception;
    List<MedicineDetails>  getPrescriptionDetailsByVisitId( Integer visitID) throws Exception;
    public List<PreviousVisit> getVisitDetails(int patientId) throws Exception;
}
