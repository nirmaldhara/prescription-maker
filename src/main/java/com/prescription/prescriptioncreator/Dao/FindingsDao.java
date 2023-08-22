package com.prescription.prescriptioncreator.Dao;

import com.prescription.prescriptioncreator.model.FindingsDetails;
import com.prescription.prescriptioncreator.model.PreviousHistoryDetails;

import java.util.List;

public interface FindingsDao {
    public boolean addFindings(FindingsDetails findingsDetails) throws Exception;
    List<FindingsDetails> getAutoSuggestFindings() throws Exception;

    List<FindingsDetails> addFindings(String findings) throws Exception;
}
