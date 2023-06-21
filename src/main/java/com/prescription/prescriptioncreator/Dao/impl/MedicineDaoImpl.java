package com.prescription.prescriptioncreator.Dao.impl;

import com.prescription.prescriptioncreator.Dao.MedicineDao;
import com.prescription.prescriptioncreator.model.MedicineDetails;
import com.prescription.prescriptioncreator.model.PatientDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;

import static com.prescription.prescriptioncreator.util.DBConnection.getConnection;

public class MedicineDaoImpl implements MedicineDao {
    public  void addMedicine(MedicineDetails medicineDetails) throws Exception {
        String sql = " insert into medicine (medicine_name, dose1, dose2, dose3, dose4, dose5, dose6, when_bf_af, no_of_days, note) values (?, ?, ?, ?, ?,?,?,?,?,?)";
        Connection conn=getConnection();
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString (1, medicineDetails.getMedicineName());
            preparedStmt.setString (2, medicineDetails.getDose1());
            preparedStmt.setString (3, medicineDetails.getDose2());
            preparedStmt.setString(4,  medicineDetails.getDose3());
            preparedStmt.setString(5,  medicineDetails.getDose4());
            preparedStmt.setString(6,  medicineDetails.getDose5());
            preparedStmt.setString(7,  medicineDetails.getDose6());
            preparedStmt.setString(8,  medicineDetails.getWhen());
            preparedStmt.setString(9,  medicineDetails.getNoOfDays());
            preparedStmt.setString(10,  medicineDetails.getNote());
            preparedStmt.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
