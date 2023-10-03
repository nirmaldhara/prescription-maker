package com.prescription.prescriptioncreator.service;

import com.prescription.prescriptioncreator.model.SuggestionsDetails;

import java.util.List;

public interface P_SuggestionsService {
    public void saveP_Suggestions(List<SuggestionsDetails> lstSuggestionsDetails, int suggestionsId) throws Exception;

}
