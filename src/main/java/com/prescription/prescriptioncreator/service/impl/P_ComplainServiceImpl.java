package com.prescription.prescriptioncreator.service.impl;

import com.prescription.prescriptioncreator.Dao.P_ComplainDao;
import com.prescription.prescriptioncreator.Dao.impl.P_ComplainDaoImpl;
import com.prescription.prescriptioncreator.model.ComplainDetails;
import com.prescription.prescriptioncreator.service.P_ComplainService;

import java.util.List;

public class P_ComplainServiceImpl implements P_ComplainService {
    @Override
    public void saveP_ComplainDao(List<ComplainDetails> lstComplainDetails, int complainId) throws Exception {
        P_ComplainDao pcomplainDao = new P_ComplainDaoImpl();
        pcomplainDao.saveP_ComplainDao(lstComplainDetails,complainId);
    }
}
