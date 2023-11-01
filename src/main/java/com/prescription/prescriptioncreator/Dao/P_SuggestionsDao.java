package com.prescription.prescriptioncreator.Dao;

import com.prescription.prescriptioncreator.model.SuggestionsDetails;

import java.util.List;

public interface P_SuggestionsDao {
    public void saveP_Suggestions(List<SuggestionsDetails> lstSuggestionsDetails, int suggestionsId) throws Exception;
}
