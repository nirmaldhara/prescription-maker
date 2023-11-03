package com.prescription.prescriptioncreator.service;

import com.prescription.prescriptioncreator.model.MedicineDetails;
import com.prescription.prescriptioncreator.model.PatientDetails;

import java.util.List;

public interface MedicineService {
    long addMedicine(MedicineDetails medicineDetails) throws Exception;
     List<MedicineDetails> getAutoSuggestMedicine() throws Exception;

    }
