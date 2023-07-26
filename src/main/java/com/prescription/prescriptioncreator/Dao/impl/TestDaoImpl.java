package com.prescription.prescriptioncreator.Dao.impl;

import com.prescription.prescriptioncreator.Dao.TestDao;
import com.prescription.prescriptioncreator.model.TestDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import static com.prescription.prescriptioncreator.util.DBConnection.getConnection;

public class TestDaoImpl implements TestDao {
    @Override
    public void saveTest(List<TestDetails> testDetails, int patientId) throws Exception {
        String sql = " insert into lab_test (test_name, test_value) values (?, ?)";
        Connection conn=getConnection();
        for(TestDetails td:testDetails){
          try{
              PreparedStatement preparedStmt = conn.prepareStatement(sql);
              preparedStmt.setString(1, td.getTest_name());
              preparedStmt.setString (2,td.getTest_value());
              preparedStmt.execute();
           } catch(Exception e) {
              throw new RuntimeException(e);
          }
        }
    }
}
