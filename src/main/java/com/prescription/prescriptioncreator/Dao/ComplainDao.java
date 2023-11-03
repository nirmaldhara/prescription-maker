package com.prescription.prescriptioncreator.Dao;

import com.prescription.prescriptioncreator.model.ComplainDetails;
import com.prescription.prescriptioncreator.model.PreviousHistoryDetails;

import java.util.List;

public interface ComplainDao {
    public long addComplain(String complain) throws Exception;
    List<ComplainDetails> getAutoSuggestComplain() throws Exception;
    public void saveComplainToPrescription(List<ComplainDetails> lstComplainDetails, long visit_id) throws Exception;

}
