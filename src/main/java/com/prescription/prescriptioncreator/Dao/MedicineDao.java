package com.prescription.prescriptioncreator.Dao;

import com.prescription.prescriptioncreator.model.MedicineDetails;

public interface MedicineDao {
    public void addMedicine(MedicineDetails medicineDetails)throws Exception;
}
