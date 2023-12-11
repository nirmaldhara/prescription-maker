package com.prescription.prescriptioncreator.service;

import com.prescription.prescriptioncreator.model.DiagnosisDetails;

import java.util.List;

public interface DiagnosisService {
    public long addDiagnosis(String diagnosis) throws Exception;

    List<DiagnosisDetails> getAutoSuggestDiagnosis() throws Exception;

    public void saveDiagnosisToPrescription(List<DiagnosisDetails> lstDiagnosisDetails, long visit_id) throws Exception;

    public List<DiagnosisDetails> getDiagnosisOFDetails(long visitId) throws Exception;
}
