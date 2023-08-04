package com.prescription.prescriptioncreator.service.impl;

import com.prescription.prescriptioncreator.Dao.TestDao;
import com.prescription.prescriptioncreator.Dao.impl.TestDaoImpl;
import com.prescription.prescriptioncreator.model.TestDetails;
import com.prescription.prescriptioncreator.service.TestService;

import java.util.List;

public class TestServiceImpl implements TestService {
    TestDao testDao = new TestDaoImpl();
    @Override
    public void saveTest(List<TestDetails> testDetails, int patientId) throws Exception {

        testDao.saveTest(testDetails,patientId);
    }
    @Override
    public List<TestDetails> getAutoSuggestTest() throws Exception {
        return testDao.getAutoSuggestTest();
    }
}
