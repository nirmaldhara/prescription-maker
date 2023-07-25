package com.prescription.prescriptioncreator.service;

import com.prescription.prescriptioncreator.model.TestDetails;

public interface TestService {
    boolean addTest(TestDetails testDetails) throws Exception;
}
