package com.prescription.prescriptioncreator.service.impl;

import com.prescription.prescriptioncreator.Dao.SuggestionsDao;
import com.prescription.prescriptioncreator.Dao.impl.SuggestionsDaoImpl;
import com.prescription.prescriptioncreator.model.SuggestionsDetails;
import com.prescription.prescriptioncreator.service.SuggestionsService;

import java.util.List;

public class SuggestionsServiceImpl implements SuggestionsService {
    SuggestionsDao suggestionsDao = new SuggestionsDaoImpl();
    @Override
    public boolean addSuggestions(SuggestionsDetails suggestionsDetails) throws Exception {
        return suggestionsDao.addSuggestions(suggestionsDetails);
    }

    @Override
    public List<SuggestionsDetails> getAutoSuggestSuggestions() throws Exception {
        return suggestionsDao.getAutoSuggestSuggestions();
    }

    @Override
    public List<SuggestionsDetails> addSuggestions(String suggestions) throws Exception {
        return suggestionsDao.addSuggestions(suggestions);
    }
}
