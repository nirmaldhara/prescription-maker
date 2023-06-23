package com.prescription.prescriptioncreator.service.impl;

import com.prescription.prescriptioncreator.Dao.PrescriptionDao;
import com.prescription.prescriptioncreator.Dao.impl.PrescriptionDaoImpl;
import com.prescription.prescriptioncreator.model.PrescriptionDetails;
import com.prescription.prescriptioncreator.service.PrescriptionService;

public class PrescriptionServiceImpl implements PrescriptionService {
    public void addPrescription(PrescriptionDetails prescriptionDetails) throws Exception{
        PrescriptionDao prescriptionDao = new PrescriptionDaoImpl();
        prescriptionDao.addPrescription(prescriptionDetails);
    }
}
