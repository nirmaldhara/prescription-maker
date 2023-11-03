package com.prescription.prescriptioncreator.service.impl;

import com.prescription.prescriptioncreator.Dao.ComplainDao;
import com.prescription.prescriptioncreator.Dao.impl.ComplainDaoImpl;
import com.prescription.prescriptioncreator.model.ComplainDetails;
import com.prescription.prescriptioncreator.service.ComplainService;

import java.util.List;

public class ComplainServiceImpl implements ComplainService {
    ComplainDao complainDao = new ComplainDaoImpl();
    @Override
    public long addComplain(String comdetails) throws Exception {
        return complainDao.addComplain(comdetails);
    }

    @Override
    public List<ComplainDetails> getAutoSuggestComplain() throws Exception {
        return complainDao.getAutoSuggestComplain();
    }

    @Override
    public void saveComplainToPrescription(List<ComplainDetails> lstComplainDetails, long visit_id) throws Exception {
        complainDao.saveComplainToPrescription(lstComplainDetails,visit_id);
    }


}
