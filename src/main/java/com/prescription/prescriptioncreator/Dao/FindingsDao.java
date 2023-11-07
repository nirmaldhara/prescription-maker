package com.prescription.prescriptioncreator.Dao;

import com.prescription.prescriptioncreator.model.FindingsDetails;
import com.prescription.prescriptioncreator.model.PreviousHistoryDetails;

import java.util.List;

public interface FindingsDao {
    public long addFindings(String findings) throws Exception;

    List<FindingsDetails> getAutoSuggestFindings() throws Exception;

    public void saveFindingsToPrescription(List<FindingsDetails> lstFindingsDetails, long visit_id) throws Exception;

    public List<FindingsDetails> getFindingsOFDetails(long visitId) throws Exception;
}
