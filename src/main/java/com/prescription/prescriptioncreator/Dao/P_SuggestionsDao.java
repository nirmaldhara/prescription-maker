package com.prescription.prescriptioncreator.Dao;

import com.prescription.prescriptioncreator.model.DiagnosisDetails;

import java.util.List;

public interface P_SuggestionsDao {
    public void saveP_Suggestions(List<DiagnosisDetails> lstSuggestionsDetails, int suggestionsId) throws Exception;
}
