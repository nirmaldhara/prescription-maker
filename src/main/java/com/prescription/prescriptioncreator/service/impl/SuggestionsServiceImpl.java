package com.prescription.prescriptioncreator.service.impl;

import com.prescription.prescriptioncreator.Dao.SuggestionsDao;
import com.prescription.prescriptioncreator.Dao.impl.SuggestionsDaoImpl;
import com.prescription.prescriptioncreator.model.SuggestionsDetails;
import com.prescription.prescriptioncreator.service.SuggestionsService;

import java.util.List;

public class SuggestionsServiceImpl implements SuggestionsService {
    SuggestionsDao suggestionsDao = new SuggestionsDaoImpl();

    @Override
    public long addSuggestions(String suggestions) throws Exception {
        return suggestionsDao.addSuggestions(suggestions);
    }

    @Override
    public List<SuggestionsDetails> getAutoSuggestSuggestions() throws Exception {
        return suggestionsDao.getAutoSuggestSuggestions();
    }

    @Override
    public void saveSuggestionsToPrescription(List<SuggestionsDetails> lstSuggestionsDetails, long visit_id) throws Exception {
        suggestionsDao.saveSuggestionsToPrescription(lstSuggestionsDetails, visit_id);
    }

    @Override
    public List<SuggestionsDetails> getSuggestionsOFDetails(long visitId) throws Exception {
        return suggestionsDao.getSuggestionsOFDetails(visitId);
    }
}
