package com.prescription.prescriptioncreator.Dao;

import com.prescription.prescriptioncreator.model.PrescriptionDetails;

public interface PrescriptionDao {
    public void addPrescription(PrescriptionDetails prescriptionDetails) throws Exception;
}
