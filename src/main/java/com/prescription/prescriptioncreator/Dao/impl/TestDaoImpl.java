package com.prescription.prescriptioncreator.Dao.impl;

import com.prescription.prescriptioncreator.Dao.TestDao;
import com.prescription.prescriptioncreator.model.TestDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static com.prescription.prescriptioncreator.util.DBConnection.getConnection;

public class TestDaoImpl implements TestDao {
    /* @method saveTest
     * @param testDetails
     * @throws Exception
     * @description saving test details for the patients in lab_test table
     * @developer Sukhendu
     */
    /* @method getAutoSuggestTest
     * @throws Exception
     * @description to get auto suggest test
     * @developer Sukhendu
     */
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
    @Override
    public List<TestDetails> getAutoSuggestTest() throws Exception {
        String dbsql = "SELECT `id`,`test_name`,`test_value`FROM `lab_test`";
        List<TestDetails> lstTestDetails=new ArrayList<>();
        PreparedStatement preparedStmt =null;
        ResultSet rs = null;
        Connection conn = getConnection();
        try{
            preparedStmt=  conn.prepareStatement(dbsql);

            rs = preparedStmt.executeQuery();
            while(rs.next()){
                TestDetails td= new TestDetails();
                td.setTest_name(rs.getString("test_name"));
                td.setTest_value(rs.getString("test_value"));
                lstTestDetails.add(td);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        finally {
            if(preparedStmt!=null)
                preparedStmt.close();
            if(rs!=null)
                rs.close();

        }
        return lstTestDetails;
    }
}
