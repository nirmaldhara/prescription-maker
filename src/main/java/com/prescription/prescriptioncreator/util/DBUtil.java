package com.prescription.prescriptioncreator.util;
import com.prescription.prescriptioncreator.PatientDetails;

import java.sql.*;

public class DBUtil {

    private static Connection conn = null;

    static Connection getConnection() throws Exception {
        if (conn == null) {
            String url = "jdbc:mysql://localhost:3306/";
            String dbName = "ntaj916db";
            String driver = "com.mysql.jdbc.Driver";
            String userName = "root";
            String password = "root";

            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url + dbName, userName, password);
        }

        return conn;
    }


    static void addUser(PatientDetails patientDetails) throws SQLException {
        String sql = " insert into patient (first_name, last_name, age, sex, mobile_no,patient_id,address) values (?, ?, ?, ?, ?,?,?)";
        try(Connection conn=getConnection() ) {
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
    static void searchUser(PatientDetails patientDetails) throws SQLException{
        String dbsql = "select first_name, last_name, age, sex, mobile_no,patient_id,address from patient where id = ?";
        try(Connection conn = getConnection()){
            PreparedStatement preparedStmt = conn.prepareStatement(dbsql);
            ResultSet rs = null;
            int id = 0;
            preparedStmt.setInt(1,id);
            rs = preparedStmt.executeQuery();
            while(rs.next()){
                System.out.println(rs.getInt(1));
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        finally {
            conn.close();
        }
    }
}
