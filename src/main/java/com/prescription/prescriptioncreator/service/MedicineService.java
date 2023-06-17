package com.prescription.prescriptioncreator.service;

import com.prescription.prescriptioncreator.model.MedicineDetails;
import com.prescription.prescriptioncreator.model.PatientDetails;

public interface MedicineService {
    void addMedicine(MedicineDetails medicineDetails) throws Exception;
}
