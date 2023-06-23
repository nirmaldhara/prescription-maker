package com.prescription.prescriptioncreator.Dao.impl;

import com.prescription.prescriptioncreator.Dao.PrescriptionDao;
import com.prescription.prescriptioncreator.model.PatientDetails;
import com.prescription.prescriptioncreator.model.PrescriptionDetails;
import com.prescription.prescriptioncreator.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.prescription.prescriptioncreator.util.DBConnection.getConnection;

public class PrescriptionDaoImpl implements PrescriptionDao {
    public void addPrescription(PrescriptionDetails prescriptionDetails) throws Exception{
        String sql = " insert into prescription (patientid,medicine_name,when_bf_af,no_of_days, dose1, dose2, dose3, dose4, dose5, dose6,note) values (?, ?, ?, ?, ?,?,?,?,?,?,?)";
        Connection conn=getConnection();
        try{
            PreparedStatement preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setInt (1,prescriptionDetails.getPatientid());
            preparedStmt.setString(2,prescriptionDetails.getMedicineName());
            preparedStmt.setString(3,prescriptionDetails.getWhen());
            preparedStmt.setString(4,prescriptionDetails.getNoOfDays());
            preparedStmt.setString(5,prescriptionDetails.getDose1());
            preparedStmt.setString(6,prescriptionDetails.getDose2());
            preparedStmt.setString(7,prescriptionDetails.getDose3());
            preparedStmt.setString(8,prescriptionDetails.getDose4());
            preparedStmt.setString(9,prescriptionDetails.getDose5());
            preparedStmt.setString(10,prescriptionDetails.getDose6());
            preparedStmt.setString(11,prescriptionDetails.getNote());
            preparedStmt.execute();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
