package com.prescription.prescriptioncreator.Dao;

import com.prescription.prescriptioncreator.model.TestDetails;

import java.util.List;

public interface TestDao {
    public void saveTest(List<TestDetails> testDetails,int patientId) throws Exception;
    List<TestDetails> getAutoSuggestTest() throws Exception;
}
