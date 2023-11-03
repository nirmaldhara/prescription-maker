package com.prescription.prescriptioncreator.service;

import com.prescription.prescriptioncreator.model.ComplainDetails;

import java.util.List;

public interface ComplainService {
    public long addComplain(String complain) throws Exception;
    List<ComplainDetails> getAutoSuggestComplain() throws Exception;

    public void saveComplainToPrescription(List<ComplainDetails> lstComplainDetails, long visit_id) throws Exception;

}
