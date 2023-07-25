package com.prescription.prescriptioncreator.service.impl;

import com.prescription.prescriptioncreator.Dao.TestDao;
import com.prescription.prescriptioncreator.Dao.impl.TestDaoImpl;
import com.prescription.prescriptioncreator.model.TestDetails;
import com.prescription.prescriptioncreator.service.TestService;

public class TestServiceImpl implements TestService {
    @Override
    public boolean addTest(TestDetails testDetails) throws Exception {
        TestDao testDao = new TestDaoImpl();
        return testDao.addTest(testDetails);
    }
}
