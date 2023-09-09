package com.prescription.prescriptioncreator.Dao;

import com.prescription.prescriptioncreator.model.MedicineDetails;
import com.prescription.prescriptioncreator.model.PreviousHistoryDetails;
import com.prescription.prescriptioncreator.model.PreviousVisit;

import java.util.List;

public interface P_Previous_HistoryDao {
    public void saveP_Previous_HistoryDao(List<PreviousHistoryDetails> lstPreviousHistoryDetails, int previousHistoryId) throws Exception;
}
