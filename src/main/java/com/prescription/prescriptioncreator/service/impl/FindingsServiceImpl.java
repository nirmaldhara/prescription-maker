package com.prescription.prescriptioncreator.service.impl;

import com.prescription.prescriptioncreator.Dao.FindingsDao;
import com.prescription.prescriptioncreator.Dao.impl.FindingsDaoImpl;
import com.prescription.prescriptioncreator.model.FindingsDetails;
import com.prescription.prescriptioncreator.service.FindingsService;

import java.util.List;

public class FindingsServiceImpl implements FindingsService {
    FindingsDao findingsDao = new FindingsDaoImpl();

    @Override
    public long addFindings(String findings) throws Exception {
        return findingsDao.addFindings(findings);
    }

    @Override
    public List<FindingsDetails> getAutoSuggestFindings() throws Exception {
        return findingsDao.getAutoSuggestFindings();
    }

    @Override
    public void saveFindingsToPrescription(List<FindingsDetails> lstFindingsDetails, long visit_id) throws Exception {
        findingsDao.saveFindingsToPrescription(lstFindingsDetails, visit_id);
    }

    @Override
    public List<FindingsDetails> getFindingsOFDetails(long visitId) throws Exception {
        return findingsDao.getFindingsOFDetails(visitId);
    }
}
