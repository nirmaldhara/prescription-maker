package com.prescription.prescriptioncreator.util;

import com.prescription.prescriptioncreator.model.PatientDetails;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUtil {


//
//    static void addPatient(PatientDetails patientDetails) throws SQLException {
//        String sql = " insert into patient (first_name, last_name, age, sex, mobile_no,patient_id,address) values (?, ?, ?, ?, ?,?,?)";
//        try(Connection conn=getConnection()) {
//            PreparedStatement preparedStmt = conn.prepareStatement(sql);
//            preparedStmt.setString (1, patientDetails.getFirst_name());
//            preparedStmt.setString (2, patientDetails.getLast_name());
//            preparedStmt.setInt   (3, patientDetails.getAge());
//            preparedStmt.setString(4, patientDetails.getSex());
//            preparedStmt.setString(5, patientDetails.getMobile_no());
//            preparedStmt.setString(6, patientDetails.getPatientId());
//            preparedStmt.setString(7, patientDetails.getAddress());
//            preparedStmt.execute();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        finally {
//            conn.close();
//        }



   // }
}
