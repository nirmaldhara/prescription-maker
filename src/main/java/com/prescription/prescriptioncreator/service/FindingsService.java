package com.prescription.prescriptioncreator.service;

import com.prescription.prescriptioncreator.model.FindingsDetails;

import java.util.List;

public interface FindingsService {
    public long addFindings(String findings) throws Exception;

    List<FindingsDetails> getAutoSuggestFindings() throws Exception;

    public void saveFindingsToPrescription(List<FindingsDetails> lstFindingsDetails, long visit_id) throws Exception;

    public List<FindingsDetails> getFindingsOFDetails(long visitId) throws Exception;
}
