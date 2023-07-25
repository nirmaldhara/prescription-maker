package com.prescription.prescriptioncreator.Dao;

import com.prescription.prescriptioncreator.model.TestDetails;

public interface TestDao {
    public boolean addTest(TestDetails testDetails) throws Exception;
}
