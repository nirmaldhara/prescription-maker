package com.prescription.prescriptioncreator.Dao;

import com.prescription.prescriptioncreator.model.MedicineDetails;
import com.prescription.prescriptioncreator.model.PreviousHistoryDetails;

import java.util.List;

public interface PreviousHistoryDao {
    public long addPreviousHistory(String previous_history) throws Exception;

    List<PreviousHistoryDetails> getAutoSuggestPreviousHistory() throws Exception;

    public void savePreviousHistoryToPrescription(List<PreviousHistoryDetails> lstPreviousHistoryDetails, long visit_id) throws Exception;

    public List<PreviousHistoryDetails> getPreviousHistoryOFDetails(long visitId) throws Exception;
}
