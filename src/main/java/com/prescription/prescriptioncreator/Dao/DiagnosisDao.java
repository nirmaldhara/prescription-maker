package com.prescription.prescriptioncreator.Dao;

import com.prescription.prescriptioncreator.model.DiagnosisDetails;

import java.util.List;

public interface DiagnosisDao {
    public long addDiagnosis(String diagnosis) throws Exception;

    List<DiagnosisDetails> getAutoSuggestDiagnosis() throws Exception;

    public void saveDiagnosisToPrescription(List<DiagnosisDetails> lstDiagnosisDetails, long visit_id) throws Exception;

    public List<DiagnosisDetails> getDiagnosisOFDetails(long visitId) throws Exception;
}
