package com.prescription.prescriptioncreator.service;

import com.prescription.prescriptioncreator.model.PreviousHistoryDetails;

import java.util.List;

public interface PreviousHistoryService {
    public boolean addPreviousHistory(PreviousHistoryDetails previousHistoryDetails) throws Exception;
    List<PreviousHistoryDetails> getAutoSuggestPreviousHistory() throws Exception;
}
