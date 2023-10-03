package com.prescription.prescriptioncreator.service.impl;

import com.prescription.prescriptioncreator.Dao.P_FindingsDao;
import com.prescription.prescriptioncreator.Dao.impl.P_FindingsDaoImpl;
import com.prescription.prescriptioncreator.model.FindingsDetails;
import com.prescription.prescriptioncreator.service.P_FindingsService;

import java.util.List;

public class P_FindingsServiceImpl implements P_FindingsService {
    @Override
    public void saveP_Findings(List<FindingsDetails> lstFindingsDetails, int findingsId) throws Exception {
        P_FindingsDao findingsDao = new P_FindingsDaoImpl();
        findingsDao.saveP_Findings(lstFindingsDetails,findingsId);
    }
}
