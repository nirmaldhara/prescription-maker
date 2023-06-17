package com.prescription.prescriptioncreator.Dao.impl;

import com.prescription.prescriptioncreator.Dao.MedicineDao;
import com.prescription.prescriptioncreator.model.MedicineDetails;
import com.prescription.prescriptioncreator.model.PatientDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;

import static com.prescription.prescriptioncreator.util.DBConnection.getConnection;

public class MedicineDaoImpl implements MedicineDao {
    public  void addMedicine(MedicineDetails medicineDetails) throws Exception {
        String sql = " insert into medicine (medicine_name, t1, t2, t3, t4, t5, t6, fr, days, note) values (?, ?, ?, ?, ?,?,?,?,?,?)";
        Connection conn=getConnection();
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString (1, medicineDetails.getMedicine_name());
            preparedStmt.setString (2, medicineDetails.getT1());
            preparedStmt.setString (3, medicineDetails.getT2());
            preparedStmt.setString(4,  medicineDetails.getT3());
            preparedStmt.setString(5,  medicineDetails.getT4());
            preparedStmt.setString(6,  medicineDetails.getT5());
            preparedStmt.setString(7,  medicineDetails.getT6());
            preparedStmt.setString(8,  medicineDetails.getFr());
            preparedStmt.setString(9,  medicineDetails.getDays());
            preparedStmt.setString(10,  medicineDetails.getNote());
            preparedStmt.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
