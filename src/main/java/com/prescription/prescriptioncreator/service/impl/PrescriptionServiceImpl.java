package com.prescription.prescriptioncreator.service.impl;

import com.prescription.prescriptioncreator.Dao.PrescriptionDao;
import com.prescription.prescriptioncreator.Dao.impl.PrescriptionDaoImpl;
import com.prescription.prescriptioncreator.model.MedicineDetails;
import com.prescription.prescriptioncreator.model.PrescriptionDetails;
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
    public long saveVisitHistory(long patient_id,long visit_id, Date visitDate, Date nextVisitDate, float weight, float height) throws Exception {
        return prescriptionDao.saveVisitHistory(patient_id,visit_id,visitDate,nextVisitDate,weight,height);
    }

    @Override
    public List<MedicineDetails> getPrescriptionDetailsByVisitId(Integer visitID) throws Exception {
        return prescriptionDao.getPrescriptionDetailsByVisitId(visitID);
    }


    @Override
    public List<PreviousVisit> getVisitDetails(int patientId) throws Exception {
        return prescriptionDao.getVisitDetails(patientId);
    }
}
