package com.prescription.prescriptioncreator.service.impl;

import com.prescription.prescriptioncreator.Dao.PrescriptionDao;
import com.prescription.prescriptioncreator.Dao.impl.PrescriptionDaoImpl;
import com.prescription.prescriptioncreator.model.AbdomenDetails;
import com.prescription.prescriptioncreator.model.LungsDetails;
import com.prescription.prescriptioncreator.model.MedicineDetails;
import com.prescription.prescriptioncreator.model.PreviousVisit;
import com.prescription.prescriptioncreator.service.PrescriptionService;

import java.sql.Date;
import java.util.List;

public class PrescriptionServiceImpl implements PrescriptionService {
    PrescriptionDao prescriptionDao= new PrescriptionDaoImpl();
    @Override
    public long saveNPrintPrescription(List<MedicineDetails> lstMedicineDetails, int patientId) throws Exception {
       return prescriptionDao.saveNPrintPrescription(lstMedicineDetails,patientId);
    }

    @Override
    public long saveVisitHistory(long patient_id,long visit_id, Date visitDate, Date nextVisitDate, float weight, float height,String bp, float pulse) throws Exception {
        return prescriptionDao.saveVisitHistory(patient_id,visit_id,visitDate,nextVisitDate,weight,height,bp,pulse);
    }
    @Override
    public List<MedicineDetails> getPrescriptionDetailsByVisitId(Integer visitID) throws Exception {
        return prescriptionDao.getPrescriptionDetailsByVisitId(visitID);
    }


    @Override
    public List<PreviousVisit> getVisitDetails(int patientId) throws Exception {
        return prescriptionDao.getVisitDetails(patientId);
    }

    @Override
    public long saveLungsHistory(long visit_id, String lungs_point1, String lungs_point2, String lungs_point3, String lungs_point4, String lungs_point5, String lungs_point6) throws Exception {
        return prescriptionDao.saveLungsHistory(visit_id,lungs_point1,lungs_point2,lungs_point3,lungs_point4,lungs_point5,lungs_point6);
    }

    @Override
    public LungsDetails getLungsDetails(int visitId) throws Exception {
        return prescriptionDao.getLungsDetails(visitId);
    }

    @Override
    public long saveAbdomenHistory(long visit_id, String abdomen_point1, String abdomen_point2, String abdomen_point3, String abdomen_point4, String abdomen_point5, String abdomen_point6, String abdomen_point7, String abdomen_point8, String abdomen_point9) throws Exception {
        return prescriptionDao.saveAbdomenHistory(visit_id,abdomen_point1,abdomen_point2,abdomen_point3,abdomen_point4,abdomen_point5,abdomen_point6,abdomen_point7,abdomen_point8,abdomen_point9);
    }

    @Override
    public AbdomenDetails getAbdomenDetails(int visitId) throws Exception {
        return prescriptionDao.getAbdomenDetails(visitId);
    }

    /*@Override
    public List<PreviousVisit> getAutoSuggestPreviousVisit() throws Exception {
        return prescriptionDao.getAutoSuggestPreviousVisit();
    }*/
}
