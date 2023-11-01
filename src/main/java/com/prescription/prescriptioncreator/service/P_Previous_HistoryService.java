package com.prescription.prescriptioncreator.service;

import com.prescription.prescriptioncreator.model.PreviousHistoryDetails;

import java.util.List;

public interface P_Previous_HistoryService {
    public void saveP_Previous_HistoryDao(List<PreviousHistoryDetails> lstPreviousHistoryDetails, int previousHistoryId) throws Exception;
}
