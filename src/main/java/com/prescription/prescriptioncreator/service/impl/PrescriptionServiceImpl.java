package com.prescription.prescriptioncreator.service.impl;

import com.prescription.prescriptioncreator.Dao.MedicineDao;
import com.prescription.prescriptioncreator.Dao.PrescriptionDao;
import com.prescription.prescriptioncreator.Dao.impl.MedicineDaoImpl;
import com.prescription.prescriptioncreator.Dao.impl.PrescriptionDaoImpl;
import com.prescription.prescriptioncreator.model.MedicineDetails;
import com.prescription.prescriptioncreator.model.PrescriptionDetails;
import com.prescription.prescriptioncreator.service.PrescriptionService;

public class PrescriptionServiceImpl implements PrescriptionService {
    public void addPrescription(MedicineDetails medicineDetails) throws Exception{//////
        PrescriptionDao prescriptionDao = new PrescriptionDaoImpl();
        prescriptionDao.addPrescription(medicineDetails);/////
        ////////

    }
}
