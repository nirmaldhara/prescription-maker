package com.prescription.prescriptioncreator.service.impl;

import com.prescription.prescriptioncreator.Dao.PreviousHistoryDao;
import com.prescription.prescriptioncreator.Dao.impl.PreviousHistoryDaoImpl;
import com.prescription.prescriptioncreator.model.PreviousHistoryDetails;
import com.prescription.prescriptioncreator.service.PreviousHistoryService;

import java.util.List;

public class PreviousHistoryServiceImpl implements PreviousHistoryService {
    PreviousHistoryDao previousHistoryDao = new PreviousHistoryDaoImpl();
    @Override
    public boolean addPreviousHistory(PreviousHistoryDetails previousHistoryDetails) throws Exception {
        return previousHistoryDao.addPreviousHistory(previousHistoryDetails);
    }

    @Override
    public List<PreviousHistoryDetails> getAutoSuggestPreviousHistory() throws Exception {
        return previousHistoryDao.getAutoSuggestPreviousHistory();
    }
}
