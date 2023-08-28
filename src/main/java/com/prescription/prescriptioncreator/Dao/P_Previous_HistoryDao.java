package com.prescription.prescriptioncreator.Dao;

import com.prescription.prescriptioncreator.model.MedicineDetails;

import java.util.List;

public interface P_Previous_HistoryDao {
    public void saveP_Previous_HistoryDao(int previousHistoryId,int visitId) throws Exception;
}
