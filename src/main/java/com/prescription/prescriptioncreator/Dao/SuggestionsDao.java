package com.prescription.prescriptioncreator.Dao;

import com.prescription.prescriptioncreator.model.FindingsDetails;
import com.prescription.prescriptioncreator.model.SuggestionsDetails;

import java.util.List;

public interface SuggestionsDao {
    public boolean addSuggestions(SuggestionsDetails suggestionsDetails) throws Exception;
    List<SuggestionsDetails> getAutoSuggestSuggestions() throws Exception;

    List<SuggestionsDetails> addSuggestions(String suggestions) throws Exception;
}
