package com.prescription.prescriptioncreator.Dao;

import com.prescription.prescriptioncreator.model.ComplainDetails;

import java.util.List;

public interface P_ComplainDao {
    public void saveP_ComplainDao(List<ComplainDetails> lstComplainDetails, int complainId) throws Exception;

}
