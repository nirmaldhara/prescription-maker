package com.prescription.prescriptioncreator.Dao;

import com.prescription.prescriptioncreator.model.FindingsDetails;

import java.util.List;

public interface P_FindingsDao {
    public void saveP_Findings(List<FindingsDetails> lstFindingsDetails, int findingsId) throws Exception;
}
