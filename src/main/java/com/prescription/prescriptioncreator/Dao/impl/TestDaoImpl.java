package com.prescription.prescriptioncreator.Dao.impl;

import com.prescription.prescriptioncreator.Dao.TestDao;
import com.prescription.prescriptioncreator.model.TestDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;

import static com.prescription.prescriptioncreator.util.DBConnection.getConnection;

public class TestDaoImpl implements TestDao {
    @Override
    public boolean addTest(TestDetails testDetails) throws Exception {
        String sql = " insert into lab_test (test_name, test_value) values (?, ?)";
        Connection conn=getConnection();
        try{
            PreparedStatement preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, testDetails.getTest_name());
            preparedStmt.setString (2,testDetails.getTest_value());
            preparedStmt.execute();
            return true;
        }catch(Exception e) {
            return false;
        }
    }
}
