package com.prescription.prescriptioncreator.Dao;

import com.prescription.prescriptioncreator.model.MedicineDetails;
import com.prescription.prescriptioncreator.model.PreviousHistoryDetails;

import java.util.List;

public interface PreviousHistoryDao {
    public boolean addPreviousHistory(PreviousHistoryDetails previousHistoryDetails) throws Exception;
    List<PreviousHistoryDetails> getAutoSuggestPreviousHistory() throws Exception;
}
