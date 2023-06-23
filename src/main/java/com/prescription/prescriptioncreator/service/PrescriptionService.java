package com.prescription.prescriptioncreator.service;

import com.prescription.prescriptioncreator.model.PrescriptionDetails;

public interface PrescriptionService {
    void addPrescription(PrescriptionDetails prescriptionDetails) throws Exception;
}
