package com.prescription.prescriptioncreator.Dao;

import com.prescription.prescriptioncreator.model.PreviousHistoryDetails;

public interface PreviousHistoryDao {
    public boolean addPreviousHistory(PreviousHistoryDetails previousHistoryDetails) throws Exception;
}
