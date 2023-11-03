package com.prescription.prescriptioncreator.service.impl;

import com.prescription.prescriptioncreator.Dao.PreviousHistoryDao;
import com.prescription.prescriptioncreator.Dao.impl.PreviousHistoryDaoImpl;
import com.prescription.prescriptioncreator.model.PreviousHistoryDetails;
import com.prescription.prescriptioncreator.service.PreviousHistoryService;

import java.util.List;

public class PreviousHistoryServiceImpl implements PreviousHistoryService {
    PreviousHistoryDao previousHistoryDao = new PreviousHistoryDaoImpl();
    @Override
    public long addPreviousHistory(String previous_history) throws Exception {
        return previousHistoryDao.addPreviousHistory(previous_history);
    }

    @Override
    public List<PreviousHistoryDetails> getAutoSuggestPreviousHistory() throws Exception {
        return previousHistoryDao.getAutoSuggestPreviousHistory();
    }

    @Override
    public void savePreviousHistoryToPrescription(List<PreviousHistoryDetails> lstPreviousHistoryDetails, long visit_id) throws Exception {
        previousHistoryDao.savePreviousHistoryToPrescription(lstPreviousHistoryDetails, visit_id);
    }

    @Override
    public List<PreviousHistoryDetails> getPreviousHistoryOFDetails(long visitId) throws Exception {
        return previousHistoryDao.getPreviousHistoryOFDetails(visitId);
    }
}
