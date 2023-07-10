package com.prescription.prescriptioncreator.service.impl;

import com.prescription.prescriptioncreator.Dao.MedicineDao;
import com.prescription.prescriptioncreator.Dao.impl.MedicineDaoImpl;
import com.prescription.prescriptioncreator.model.MedicineDetails;
import com.prescription.prescriptioncreator.service.MedicineService;

import java.util.List;

public class MedicineServiceImpl implements MedicineService {
    MedicineDao medicineDao= new MedicineDaoImpl();
    @Override
    public void addMedicine(MedicineDetails medicineDetails) throws Exception {

        medicineDao.addMedicine(medicineDetails);
    }

    @Override
    public List<MedicineDetails> getAutoSuggestMedicine() throws Exception {
        return medicineDao.getAutoSuggestMedicine();
    }


}
