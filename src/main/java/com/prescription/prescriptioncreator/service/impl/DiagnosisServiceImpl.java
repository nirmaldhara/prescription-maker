package com.prescription.prescriptioncreator.service.impl;

import com.prescription.prescriptioncreator.Dao.DiagnosisDao;
import com.prescription.prescriptioncreator.Dao.impl.DiagnosisDaoImpl;
import com.prescription.prescriptioncreator.model.DiagnosisDetails;
import com.prescription.prescriptioncreator.service.DiagnosisService;

import java.util.List;

public class DiagnosisServiceImpl implements DiagnosisService {
    DiagnosisDao diagnosisDao = new DiagnosisDaoImpl();

    @Override
    public long addDiagnosis(String diagnosis) throws Exception {
        return diagnosisDao.addDiagnosis(diagnosis);
    }

    @Override
    public List<DiagnosisDetails> getAutoSuggestDiagnosis() throws Exception {
        return diagnosisDao.getAutoSuggestDiagnosis();
    }

    @Override
    public void saveDiagnosisToPrescription(List<DiagnosisDetails> lstDiagnosisDetails, long visit_id) throws Exception {
        diagnosisDao.saveDiagnosisToPrescription(lstDiagnosisDetails, visit_id);
    }

    @Override
    public List<DiagnosisDetails> getDiagnosisOFDetails(long visitId) throws Exception {
        return diagnosisDao.getDiagnosisOFDetails(visitId);
    }
}
