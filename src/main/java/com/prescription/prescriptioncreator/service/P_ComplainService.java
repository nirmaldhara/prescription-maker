package com.prescription.prescriptioncreator.service;

import com.prescription.prescriptioncreator.model.ComplainDetails;

import java.util.List;

public interface P_ComplainService {
    public void saveP_ComplainDao(List<ComplainDetails> lstComplainDetails, int complainId) throws Exception;

}
