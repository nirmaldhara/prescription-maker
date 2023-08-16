package com.prescription.prescriptioncreator.service;

import com.prescription.prescriptioncreator.model.PreviousHistoryDetails;

public interface PreviousHistoryService {
    public boolean addPreviousHistory(PreviousHistoryDetails previousHistoryDetails) throws Exception;

}
