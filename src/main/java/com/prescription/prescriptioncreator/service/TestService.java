package com.prescription.prescriptioncreator.service;

import com.prescription.prescriptioncreator.model.TestDetails;

import java.util.List;

public interface TestService {
    void saveTest(List<TestDetails> testDetails, int patientId) throws Exception;
}
