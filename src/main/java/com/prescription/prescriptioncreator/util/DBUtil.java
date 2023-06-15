package com.prescription.prescriptioncreator.util;
import com.prescription.prescriptioncreator.model.PatientDetails;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUtil {

    private static Connection conn = null;

    static Connection getConnection() throws Exception {
        if (conn == null) {
            String url = "jdbc:mysql://localhost:3306/";
            String dbName = "ntaj916db";
            String driver = "com.mysql.cj.jdbc.Driver";
            String userName = "root";
            String password = "root";

            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url + dbName, userName, password);
        }

        return conn;
    }


    static void addUser(PatientDetails patientDetails) throws SQLException {
        String sql = " insert into patient (first_name, last_name, age, sex, mobile_no,patient_id,address) values (?, ?, ?, ?, ?,?,?)";
        try(Connection conn=getConnection()) {
            PreparedStatement preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString (1, patientDetails.getFirst_name());
            preparedStmt.setString (2, patientDetails.getLast_name());
            preparedStmt.setInt   (3, patientDetails.getAge());
            preparedStmt.setString(4, patientDetails.getSex());
            preparedStmt.setString(5, patientDetails.getMobile_no());
            preparedStmt.setString(6, patientDetails.getPatientId());
            preparedStmt.setString(7, patientDetails.getAddress());
            preparedStmt.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            conn.close();
        }



    }
    public static List<PatientDetails> searchUser(String mobile_no,String patient_id) throws SQLException{
        String dbsql = "select distinct  first_name, last_name, age, sex, mobile_no,patient_id,address from patient where mobile_no = ? or patient_id = ? ";
        List<PatientDetails> paitentList=new ArrayList<>();
        PreparedStatement preparedStmt =null;
        ResultSet rs = null;
        try(Connection conn = getConnection()){
            preparedStmt=  conn.prepareStatement(dbsql);

            preparedStmt.setString(1,mobile_no);
            preparedStmt.setString(2,patient_id);
            rs = preparedStmt.executeQuery();
            while(rs.next()){
                PatientDetails pd= new PatientDetails();
                pd.setFirst_name(rs.getString("first_name"));
                pd.setLast_name(rs.getString("last_name"));
                pd.setAge(rs.getInt("age"));
                pd.setSex(rs.getString("sex"));
                pd.setMobile_no(rs.getString("mobile_no"));
                pd.setPatientId(rs.getString("patient_id"));
                pd.setAddress(rs.getString("address"));
                paitentList.add(pd);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        finally {
            if(preparedStmt!=null)
            preparedStmt.close();
            if(rs!=null)
            rs.close();
            if(conn!=null) {
                conn.close();
                conn = null;
            }
        }
        return paitentList;
    }
}
