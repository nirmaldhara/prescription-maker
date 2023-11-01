package com.prescription.prescriptioncreator.service;

import com.prescription.prescriptioncreator.model.FindingsDetails;

import java.util.List;

public interface FindingsService {
    public boolean addFindings(FindingsDetails findingsDetails) throws Exception;
    List<FindingsDetails> getAutoSuggestFindings() throws Exception;

    List<FindingsDetails> addFindings(String findings) throws Exception;
}
