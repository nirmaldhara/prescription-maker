package com.prescription.prescriptioncreator.Dao.impl;

import com.prescription.prescriptioncreator.Dao.PrescriptionDao;
import com.prescription.prescriptioncreator.model.MedicineDetails;
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
    public void addPrescription(MedicineDetails medicineDetails) throws Exception{//////
        String sql = " insert into prescription (medicine_name,when_bf_af,no_of_days,dose1, dose2, dose3, dose4, dose5, dose6,note) values (?, ?, ?, ?,?,?,?,?,?,?)";
        Connection conn=getConnection();
        try{
            PreparedStatement preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1,medicineDetails.getMedicineName());//
            preparedStmt.setString(2,medicineDetails.getWhen());//
            preparedStmt.setString(3,medicineDetails.getNoOfDays());//

            preparedStmt.setString(4,medicineDetails.getDose1());//
            preparedStmt.setString(5,medicineDetails.getDose2());//
            preparedStmt.setString(6,medicineDetails.getDose3());//
            preparedStmt.setString(7,medicineDetails.getDose4());//
            preparedStmt.setString(8,medicineDetails.getDose5());//
            preparedStmt.setString(9,medicineDetails.getDose6());//
            preparedStmt.setString(10,medicineDetails.getNote());//
            preparedStmt.execute();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
