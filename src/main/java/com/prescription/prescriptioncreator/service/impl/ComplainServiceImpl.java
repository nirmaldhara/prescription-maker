package com.prescription.prescriptioncreator.service.impl;

import com.prescription.prescriptioncreator.Dao.ComplainDao;
import com.prescription.prescriptioncreator.Dao.impl.ComplainDaoImpl;
import com.prescription.prescriptioncreator.model.ComplainDetails;
import com.prescription.prescriptioncreator.service.ComplainService;

import java.util.List;

public class ComplainServiceImpl implements ComplainService {
    ComplainDao complainDao = new ComplainDaoImpl();
    @Override
    public long addComplain(ComplainDetails complainDetails) throws Exception {
        return complainDao.addComplain(complainDetails);
    }

    @Override
    public List<ComplainDetails> getAutoSuggestComplain() throws Exception {
        return complainDao.getAutoSuggestComplain();
    }

    @Override
    public List<ComplainDetails> addComplain(String complain) throws Exception {
        return complainDao.addComplain(complain);
    }
}
