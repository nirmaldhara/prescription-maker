package com.prescription.prescriptioncreator.Dao;

import com.prescription.prescriptioncreator.model.ComplainDetails;
import com.prescription.prescriptioncreator.model.PreviousHistoryDetails;

import java.util.List;

public interface ComplainDao {
    public long addComplain(ComplainDetails complainDetails) throws Exception;
    List<ComplainDetails> getAutoSuggestComplain() throws Exception;

    List<ComplainDetails> addComplain(String complain) throws Exception;
}
