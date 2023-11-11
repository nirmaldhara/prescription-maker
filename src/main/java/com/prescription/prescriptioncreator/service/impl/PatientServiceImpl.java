package com.prescription.prescriptioncreator.service.impl;

import com.prescription.prescriptioncreator.Dao.PatientDao;
import com.prescription.prescriptioncreator.Dao.impl.PatientDaoImpl;
import com.prescription.prescriptioncreator.model.PatientDetails;
import com.prescription.prescriptioncreator.service.PatientService;

import java.sql.SQLException;
import java.util.List;

public class PatientServiceImpl implements PatientService {
    @Override
    public List<PatientDetails> searchPatientDetails(String mobile_no, int patient_id) throws Exception {
        PatientDao patientDao= new PatientDaoImpl();
        return patientDao.searchPatientDetails(mobile_no,patient_id);
    }
@Override
public boolean addPatient(PatientDetails patientDetails) throws Exception{
    PatientDao patientDao= new PatientDaoImpl();
    return patientDao.addPatient(patientDetails);

}
}
