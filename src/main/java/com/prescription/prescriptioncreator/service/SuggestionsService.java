package com.prescription.prescriptioncreator.service;

import com.prescription.prescriptioncreator.model.SuggestionsDetails;

import java.util.List;

public interface SuggestionsService {
    public long addSuggestions(String suggestions) throws Exception;

    List<SuggestionsDetails> getAutoSuggestSuggestions() throws Exception;

    public void saveSuggestionsToPrescription(List<SuggestionsDetails> lstSuggestionsDetails, long visit_id) throws Exception;

    public List<SuggestionsDetails> getSuggestionsOFDetails(long visitId) throws Exception;
}
