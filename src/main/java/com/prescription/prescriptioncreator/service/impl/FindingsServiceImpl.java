package com.prescription.prescriptioncreator.service.impl;

import com.prescription.prescriptioncreator.Dao.FindingsDao;
import com.prescription.prescriptioncreator.Dao.impl.FindingsDaoImpl;
import com.prescription.prescriptioncreator.model.FindingsDetails;
import com.prescription.prescriptioncreator.service.FindingsService;

import java.util.List;

public class FindingsServiceImpl implements FindingsService {
    FindingsDao findingsDao = new FindingsDaoImpl();
    @Override
    public boolean addFindings(FindingsDetails findingsDetails) throws Exception {
        return findingsDao.addFindings(findingsDetails);
    }

    @Override
    public List<FindingsDetails> getAutoSuggestFindings() throws Exception {
        return findingsDao.getAutoSuggestFindings();
    }

    @Override
    public List<FindingsDetails> addFindings(String findings) throws Exception {
        return findingsDao.addFindings(findings);
    }
}
