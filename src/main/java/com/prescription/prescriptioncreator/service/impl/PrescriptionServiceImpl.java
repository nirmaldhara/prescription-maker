package com.prescription.prescriptioncreator.service.impl;

import com.prescription.prescriptioncreator.Dao.PrescriptionDao;
import com.prescription.prescriptioncreator.Dao.impl.PrescriptionDaoImpl;
import com.prescription.prescriptioncreator.model.MedicineDetails;
import com.prescription.prescriptioncreator.model.PrescriptionDetails;
import com.prescription.prescriptioncreator.model.PreviousVisit;
import com.prescription.prescriptioncreator.service.PrescriptionService;

import java.util.List;

public class PrescriptionServiceImpl implements PrescriptionService {
    @Override
    public void saveNPrintPrescription(List<MedicineDetails> lstMedicineDetails, int patientId) throws Exception {
        PrescriptionDao prescriptionDao= new PrescriptionDaoImpl();
        prescriptionDao.saveNPrintPrescription(lstMedicineDetails,patientId);
    }

    @Override
    public List<PreviousVisit> getVisitDetails(int patientId) throws Exception {
        PrescriptionDao prescriptionDao= new PrescriptionDaoImpl();


        return prescriptionDao.getVisitDetails(patientId);
    }
}
