package com.prescription.prescriptioncreator.service;

import com.prescription.prescriptioncreator.model.FindingsDetails;

import java.util.List;

public interface P_FindingsService {
    public void saveP_Findings(List<FindingsDetails> lstFindingsDetails, int findingsId) throws Exception;
}
