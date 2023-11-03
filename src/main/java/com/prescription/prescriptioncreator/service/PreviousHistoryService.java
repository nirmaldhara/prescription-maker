package com.prescription.prescriptioncreator.service;

import com.prescription.prescriptioncreator.model.PreviousHistoryDetails;

import java.util.List;

public interface PreviousHistoryService {
    public long addPreviousHistory(String previousHistoryDetails) throws Exception;
    List<PreviousHistoryDetails> getAutoSuggestPreviousHistory() throws Exception;
    public void savePreviousHistoryToPrescription(List<PreviousHistoryDetails> lstPreviousHistoryDetails,long visit_Id) throws Exception;
}
