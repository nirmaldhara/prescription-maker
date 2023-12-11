package com.prescription.prescriptioncreator.service;

import com.prescription.prescriptioncreator.model.DiagnosisDetails;

import java.util.List;

public interface P_SuggestionsService {
    public void saveP_Suggestions(List<DiagnosisDetails> lstSuggestionsDetails, int suggestionsId) throws Exception;

}
