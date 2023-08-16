package com.prescription.prescriptioncreator.service.impl;

import com.prescription.prescriptioncreator.Dao.PreviousHistoryDao;
import com.prescription.prescriptioncreator.Dao.impl.PreviousHistoryDaoImpl;
import com.prescription.prescriptioncreator.model.PreviousHistoryDetails;
import com.prescription.prescriptioncreator.service.PreviousHistoryService;

public class PreviousHistoryServiceImpl implements PreviousHistoryService {
    @Override
    public boolean addPreviousHistory(PreviousHistoryDetails previousHistoryDetails) throws Exception {
        PreviousHistoryDao previousHistoryDao = new PreviousHistoryDaoImpl();
        return previousHistoryDao.addPreviousHistory(previousHistoryDetails);
    }
}
