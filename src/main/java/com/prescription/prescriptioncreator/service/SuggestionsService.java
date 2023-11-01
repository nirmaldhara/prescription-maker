package com.prescription.prescriptioncreator.service;

import com.prescription.prescriptioncreator.model.SuggestionsDetails;

import java.util.List;

public interface SuggestionsService {
    public boolean addSuggestions(SuggestionsDetails suggestionsDetails) throws Exception;
    List<SuggestionsDetails> getAutoSuggestSuggestions() throws Exception;

    List<SuggestionsDetails> addSuggestions(String suggestions) throws Exception;
}
