package com.prescription.prescriptioncreator.service.impl;

import com.prescription.prescriptioncreator.Dao.P_SuggestionsDao;
import com.prescription.prescriptioncreator.Dao.impl.P_SuggestionsDaoImpl;
import com.prescription.prescriptioncreator.service.P_SuggestionsService;

public class P_SuggestionsServiceImpl implements P_SuggestionsService {
    @Override
    public void saveP_Suggestions(int suggestionsId, int visitIdPs) throws Exception {
        P_SuggestionsDao suggestionsDao = new P_SuggestionsDaoImpl();
        suggestionsDao.saveP_Suggestions(suggestionsId,visitIdPs);
    }
}
