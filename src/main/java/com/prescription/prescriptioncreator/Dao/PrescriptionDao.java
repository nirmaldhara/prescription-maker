package com.prescription.prescriptioncreator.Dao;

import com.prescription.prescriptioncreator.model.AbdomenDetails;
import com.prescription.prescriptioncreator.model.LungsDetails;
import com.prescription.prescriptioncreator.model.MedicineDetails;
import com.prescription.prescriptioncreator.model.PreviousVisit;

import java.sql.Date;
import java.util.List;

public interface PrescriptionDao {
    public long saveNPrintPrescription(List<MedicineDetails> lstMedicineDetails, int patientId) throws Exception;
    List<MedicineDetails>  getPrescriptionDetailsByVisitId( Integer visitID) throws Exception;
    long saveVisitHistory(long patient_id,long visit_id, Date visitDate, Date nextVisitDate, float weight, float height,String bp, float pulse) throws Exception;
    public List<PreviousVisit> getVisitDetails(int patientId) throws Exception;
    long saveLungsHistory(long visit_id, String lungs_point1, String lungs_point2, String lungs_point3, String lungs_point4, String lungs_point5, String lungs_point6) throws Exception;
    public LungsDetails getLungsDetails(int visitId) throws Exception;
    //List<PreviousVisit> getAutoSuggestPreviousVisit() throws Exception;
    long saveAbdomenHistory(long visit_id, String abdomen_point1, String abdomen_point2, String abdomen_point3, String abdomen_point4, String abdomen_point5, String abdomen_point6, String abdomen_point7, String abdomen_point8, String abdomen_point9) throws Exception;
    public AbdomenDetails getAbdomenDetails(int visitId) throws Exception;
}
