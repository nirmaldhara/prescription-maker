package com.prescription.prescriptioncreator.service.impl;

import com.prescription.prescriptioncreator.Dao.P_FindingsDao;
import com.prescription.prescriptioncreator.Dao.impl.P_FindingsDaoImpl;
import com.prescription.prescriptioncreator.service.P_FindingsService;

public class P_FindingsServiceImpl implements P_FindingsService {
    @Override
    public void saveP_Findings(int findingsId, int visitIdP) throws Exception {
        P_FindingsDao findingsDao = new P_FindingsDaoImpl();
        findingsDao.saveP_Findings(findingsId,visitIdP);
    }
}
