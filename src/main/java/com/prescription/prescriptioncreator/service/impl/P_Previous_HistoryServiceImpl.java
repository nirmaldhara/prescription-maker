package com.prescription.prescriptioncreator.service.impl;

import com.prescription.prescriptioncreator.Dao.P_Previous_HistoryDao;
import com.prescription.prescriptioncreator.Dao.impl.P_Previous_HistoryDaoImpl;
import com.prescription.prescriptioncreator.service.P_Previous_HistoryService;

public class P_Previous_HistoryServiceImpl implements P_Previous_HistoryService {
    @Override
    public void saveP_Previous_HistoryDao(int previousHistoryId, int visitId) throws Exception {
        P_Previous_HistoryDao previousHistoryDao = new P_Previous_HistoryDaoImpl();
        previousHistoryDao.saveP_Previous_HistoryDao(previousHistoryId,visitId);
    }
}
