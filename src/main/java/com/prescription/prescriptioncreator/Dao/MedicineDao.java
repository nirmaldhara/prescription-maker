package com.prescription.prescriptioncreator.Dao;

import com.prescription.prescriptioncreator.model.MedicineDetails;

import java.util.List;

public interface MedicineDao {
    public long addMedicine(MedicineDetails medicineDetails)throws Exception;

    List<MedicineDetails> getAutoSuggestMedicine() throws Exception;
}
