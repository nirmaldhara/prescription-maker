package com.prescription.prescriptioncreator.service;

import com.prescription.prescriptioncreator.model.ComplainDetails;

import java.util.List;

public interface ComplainService {
    public boolean addComplain(ComplainDetails complainDetails) throws Exception;
    List<ComplainDetails> getAutoSuggestComplain() throws Exception;

    List<ComplainDetails> addComplain(String complain) throws Exception;
}
